rootProject.name = "SchwarzMobileCommunity"

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":androidApp")
include(":shared")

// Core Modules
include(":core:model")
include(":core:ui")

// Feature Modules (API / Impl Pattern)
include(":feature:news:api")
include(":feature:news:impl")
include(":feature:meetings:api")
include(":feature:meetings:impl")