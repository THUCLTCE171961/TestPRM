pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.android.application") {
                // Workaround if 'libs' is not available here
                useVersion("8.10.0") // Directly use the version string
                // Make sure this matches your libs.versions.toml
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyticAura"
include(":app")