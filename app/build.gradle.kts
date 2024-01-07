plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.tvor_project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tvor_project"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
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
}

dependencies {
    implementation ("com.google.dagger:hilt-android:2.50")
    kapt ("com.google.dagger:hilt-compiler:2.50")
    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.5.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.5.0")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:2.1.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.1.0")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.1.0")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:2.1.0")

    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:2.1.0")
    
    // Moshi
    implementation ("com.squareup.moshi:moshi:1.8.0")
    implementation("com.google.android.datatransport:transport-runtime:3.2.0")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Moshi
    implementation("com.squareup.moshi:moshi-adapters:1.10.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.9.1")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
}