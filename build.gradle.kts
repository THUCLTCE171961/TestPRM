plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
