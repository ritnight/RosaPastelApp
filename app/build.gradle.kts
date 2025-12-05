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

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    // ANDROID & COMPOSE

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Navegación
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ViewModel + Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-rc02")

    // Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // Coil (cargar imágenes)
    implementation("io.coil-kt:coil-compose:2.7.0")

    // OpenStreetMap (si lo usas)
    implementation("org.osmdroid:osmdroid-android:6.1.18")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // TEST UNITARIOS — JUnit 4 (heredado)
    testImplementation(libs.junit)

    // TEST UNITARIOS — JUnit 5 COMPLETO
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.6")

    // KOTEST — Assertions + Runner
    testImplementation("io.kotest:kotest-runner-junit5:5.8.6")
    testImplementation("io.kotest:kotest-assertions-core:5.8.8")
    testImplementation("io.kotest:kotest-property:5.8.8")

    // MOCKK — Mocking
    testImplementation("io.mockk:mockk:1.13.10")
    androidTestImplementation("io.mockk:mockk-android:1.13.10")

    // COMPOSE UI TEST
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Espresso / JUnit Android
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// --- NECESARIO PARA QUE TODAS LAS PRUEBAS FUNCIONEN CON JUNIT 5 ---
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
