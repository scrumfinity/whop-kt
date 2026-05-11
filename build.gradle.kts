@file:OptIn(org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation::class)

plugins {
    kotlin("jvm") version "2.3.20"
    kotlin("plugin.serialization") version "2.3.20"
    id("com.google.devtools.ksp") version "2.3.7"
    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
    id("org.jetbrains.dokka") version "2.2.0"
    id("org.jetbrains.dokka-javadoc") version "2.2.0"
    `java-library`
    `maven-publish`
    signing
    kotlin("plugin.spring") version "2.3.20"
}

group = "dev.lepshee"
version = providers.gradleProperty("version").orElse("1.0-SNAPSHOT").get()

allprojects {
    group = rootProject.group
    version = rootProject.version
}

val ktorVersion = "3.4.3"
val coroutinesVersion = "1.10.2"

repositories {
    mavenCentral()
}

dependencies {
    api("io.ktor:ktor-client-core:$ktorVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    ksp(project(":whop-blocking-ksp"))

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")
    implementation(kotlin("stdlib"))
}

kotlin {
    jvmToolchain(17)

    abiValidation {
        enabled = true
    }
}

java {
    withSourcesJar()
}

ktlint {
    filter {
        exclude("**/generated/**")
        exclude { element -> element.file.path.contains("/build/generated/ksp/") }
    }
}

val integrationTestSourceSet =
    sourceSets.create("integrationTest") {
        compileClasspath += sourceSets.main.get().output + configurations.testRuntimeClasspath.get()
        runtimeClasspath += output + compileClasspath
    }

configurations[integrationTestSourceSet.implementationConfigurationName].extendsFrom(configurations.testImplementation.get())
configurations[integrationTestSourceSet.runtimeOnlyConfigurationName].extendsFrom(configurations.testRuntimeOnly.get())

val javadocJar by tasks.registering(Jar::class) {
    dependsOn(tasks.named("dokkaGeneratePublicationJavadoc"))
    archiveClassifier.set("javadoc")
    from(layout.buildDirectory.dir("dokka/javadoc"))
}

val integrationTestsEnabled =
    providers
        .environmentVariable("WHOP_INTEGRATION_TESTS")
        .map { it.equals("true", ignoreCase = true) }
        .orElse(false)

tasks.register<Test>("integrationTest") {
    description = "Runs opt-in integration tests against the Whop API."
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    testClassesDirs = integrationTestSourceSet.output.classesDirs
    classpath = integrationTestSourceSet.runtimeClasspath
    useJUnitPlatform()
    onlyIf { integrationTestsEnabled.get() }
}

val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")
val isPublishTask = gradle.startParameter.taskNames.any { it.contains("publish", ignoreCase = true) }
val publishRepositoryUrl =
    providers
        .gradleProperty("mavenRepositoryUrl")
        .orElse(providers.environmentVariable("MAVEN_REPOSITORY_URL"))
val publishRepositoryUsername =
    providers
        .gradleProperty("mavenRepositoryUsername")
        .orElse(providers.environmentVariable("MAVEN_REPOSITORY_USERNAME"))
val publishRepositoryPassword =
    providers
        .gradleProperty("mavenRepositoryPassword")
        .orElse(providers.environmentVariable("MAVEN_REPOSITORY_PASSWORD"))
val signingKey =
    providers
        .gradleProperty("signingKey")
        .orElse(providers.environmentVariable("SIGNING_KEY"))
val signingPassword =
    providers
        .gradleProperty("signingPassword")
        .orElse(providers.environmentVariable("SIGNING_PASSWORD"))

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(javadocJar)

            versionMapping {
                usage("java-api") { fromResolutionOf("runtimeClasspath") }
                usage("java-runtime") { fromResolutionResult() }
            }

            pom {
                name.set("whop-kt")
                description.set("Coroutine-friendly Kotlin/JVM SDK for the Whop API.")
                url.set("https://github.com/scrumfinity/whop-kt")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("lepshee")
                        name.set("Lepshee")
                        email.set("opensource@lepshee.dev")
                        organization.set("Lepshee")
                        organizationUrl.set("https://lepshee.dev")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/scrumfinity/whop-kt.git")
                    developerConnection.set("scm:git:ssh://git@github.com/scrumfinity/whop-kt.git")
                    url.set("https://github.com/scrumfinity/whop-kt")
                }
            }
        }
    }

    if (publishRepositoryUrl.isPresent) {
        repositories {
            maven {
                name = "release"
                url = uri(publishRepositoryUrl.get())
                credentials {
                    username = publishRepositoryUsername.orNull
                    password = publishRepositoryPassword.orNull
                }
            }
        }
    }
}

signing {
    isRequired = isReleaseVersion && isPublishTask
    if (signingKey.isPresent && signingPassword.isPresent) {
        useInMemoryPgpKeys(signingKey.get(), signingPassword.get())
    }
    sign(publishing.publications["mavenJava"])
}

tasks.test {
    useJUnitPlatform()
}
