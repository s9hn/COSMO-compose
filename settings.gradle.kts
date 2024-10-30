pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Cosmo"

include(":app")

// core
include(
    ":core:designsystem",
)

// Feature
include(
    ":feature:main",
    ":feature:home",
)

// domain
include(
    ":domain:subject",
)
