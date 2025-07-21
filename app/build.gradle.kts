plugins {
    alias(libs.plugins.android.application)
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
    implementation(libs.annotation)
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    // Cần thiết cho RecyclerView và giao diện
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.material:material:1.12.0")
    // Giao diện
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // ViewModel và LiveData (tốt cho việc quản lý dữ liệu)
    implementation("androidx.lifecycle:lifecycle-livedata:2.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.0")
    implementation ("com.jakewharton.threetenabp:threetenabp:1.4.4")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    var lifecycle_version = "2.6.2"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")


// Navigation component (rất khuyến khích)
    var nav_version = "2.7.7"
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}