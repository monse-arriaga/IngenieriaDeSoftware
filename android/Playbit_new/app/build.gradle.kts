plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "mx.ids.playbit"
    compileSdk = 34

    defaultConfig {
        applicationId = "mx.ids.playbit"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }

    buildFeatures{
        dataBinding=true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")


    //lifecycle
    fun lifecycle_version() = "2.7.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle_version()}")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version()}")
    // KSP for faster performance
    ksp("androidx.lifecycle:lifecycle-compiler:${lifecycle_version()}")


    //Room database
    fun room_version() = "2.6.1"
    implementation("androidx.room:room-runtime:${room_version()}")
    //(ksp) for faster performance
    ksp("androidx.room:room-compiler:${room_version()}")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:${room_version()}")

    // Retrofit & Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")



    //Hilt for DI
    fun hilt_version() ="2.50"
    implementation("com.google.dagger:hilt-android:${hilt_version()}")
    ksp("com.google.dagger:hilt-android-compiler:${hilt_version()}")
    //hilt viewmodel integration
    ksp("androidx.hilt:hilt-compiler:1.2.0")


    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    ksp("com.github.bumptech.glide:compiler:4.12.0")



    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    //Lottie animation
    fun lottieVersion() = "3.4.0"
    implementation("com.airbnb.android:lottie:${lottieVersion()}")


    //navigation
    fun nav_version() = "2.7.6"
    implementation("androidx.navigation:navigation-fragment-ktx:${nav_version()}")
    implementation("androidx.navigation:navigation-ui-ktx:${nav_version()}")

    //chip nav
    implementation(libs.chip.navigation.bar)
}