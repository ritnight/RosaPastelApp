plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.rosapastelapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.rosapastelapp"
        minSdk = 24
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigationevent)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // 1. NAVEGACIÓN (Guía 10)
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // 2. VIEWMODEL COMPOSE (Para usar viewModel() en @Composable)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc02")

    // 3. COROUTINES (Necesario para SharedFlow en MainViewModel)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    // 4. SUCURSALES (Para recurso nativo)
    implementation("androidx.compose.ui:ui-viewbinding:1.6.8")

    // 5. SUCURSALS (libreria de OpenStreetMap)
    implementation("org.osmdroid:osmdroid-android:6.1.18")

    //jetpack compose y material 3 (version 2025)
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    implementation("io.coil-kt:coil-compose:2.7.0")

}