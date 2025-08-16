rootProject.name = "ktor-gateway"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":server")
include(":core")
include(":client")
