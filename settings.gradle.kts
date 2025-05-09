@file:Suppress("UNUSED_EXPRESSION")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() // https://plugins.gradle.org/plugin/io.qameta.allure
    }
}

rootProject.name = "JavaAppiumAutomation"
include(":app")
include(":mylibrary")
