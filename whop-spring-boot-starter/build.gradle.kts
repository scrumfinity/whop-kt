plugins {
    kotlin("jvm") version "2.3.20"
    `java-library`
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    api(project(":"))
    api(project(":whop-spring-boot-autoconfigure"))
}
