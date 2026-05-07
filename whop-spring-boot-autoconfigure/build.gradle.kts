plugins {
    kotlin("jvm") version "2.3.20"
    `java-library`
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.5.14"))

    api(project(":"))

    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation(kotlin("reflect"))

    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(kotlin("stdlib"))
}

tasks.test {
    useJUnitPlatform()
}
