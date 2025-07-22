    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
    }

    android {
        namespace = "com.example.myticaura"
        compileSdk = 35

        defaultConfig {
            applicationId = "com.example.myticaura"
            minSdk = 24
            targetSdk = 35
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        buildFeatures {
            viewBinding = true
        }
    }

    dependencies {
        implementation("androidx.annotation:annotation:1.7.1")
        implementation(libs.androidx.activity)
        val room_version = "2.6.1"
        implementation("androidx.room:room-runtime:$room_version")
        annotationProcessor("androidx.room:room-compiler:$room_version")
        implementation("androidx.recyclerview:recyclerview:1.3.2")
        implementation("androidx.cardview:cardview:1.0.0")
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("androidx.lifecycle:lifecycle-livedata:2.8.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.0")
        implementation("com.jakewharton.threetenabp:threetenabp:1.4.4")
        implementation("com.github.bumptech.glide:glide:4.16.0")
        val lifecycle_version = "2.6.2"
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
        implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
        val nav_version = "2.7.7"
        implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
        implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        implementation ("com.google.android.gms:play-services-maps:18.2.0")
        implementation ("com.google.android.material:material:1.12.0") // Hoặc phiên bản mới hơn



    }