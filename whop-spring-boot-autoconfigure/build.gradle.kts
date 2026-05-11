plugins {
    kotlin("jvm") version "2.3.20"
    kotlin("kapt")
    `java-library`
    `maven-publish`
    signing
    id("org.jetbrains.dokka") version "2.2.0"
    id("org.jetbrains.dokka-javadoc") version "2.2.0"
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

java {
    withSourcesJar()
}

val springBootVersion = "3.5.14"

val javadocJar by tasks.registering(Jar::class) {
    dependsOn(tasks.named("dokkaGeneratePublicationJavadoc"))
    archiveClassifier.set("javadoc")
    from(layout.buildDirectory.dir("dokka/javadoc"))
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))

    api(project(":"))

    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation(kotlin("reflect"))
    kapt("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")

    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(kotlin("stdlib"))
}

tasks.test {
    useJUnitPlatform()
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
            artifactId = "whop-kt-spring-boot-autoconfigure"
            artifact(javadocJar)

            versionMapping {
                usage("java-api") { fromResolutionOf("runtimeClasspath") }
                usage("java-runtime") { fromResolutionResult() }
            }

            pom {
                name.set("whop-kt Spring Boot autoconfigure")
                description.set("Spring Boot auto-configuration for the Whop Kotlin SDK.")
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
