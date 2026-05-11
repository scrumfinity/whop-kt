plugins {
    kotlin("jvm") version "2.3.20"
    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:2.3.7")
    implementation("com.squareup:kotlinpoet:2.3.0")
    implementation("com.squareup:kotlinpoet-ksp:2.3.0")
}

kotlin {
    jvmToolchain(17)
}
