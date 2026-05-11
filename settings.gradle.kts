plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "whop-kt"

include(
    "whop-blocking-ksp",
    "whop-spring-boot-autoconfigure",
    "whop-spring-boot-starter",
)
