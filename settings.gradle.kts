plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "hexagonal"

include(":app-service")
include(":app-domain")

include(":bootstrap")

include(":user-interface:rest-api")
include(":infrastructure:orm-adapter")
include("modules")
