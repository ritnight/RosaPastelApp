# RosaPastelApp 🎨📱

Aplicación móvil desarrollada en **Android Studio** utilizando **Kotlin** y **Jetpack Compose**.  
Este proyecto forma parte del ramo *Desarrollo de Aplicaciones Móviles*.

Somos Alejandra González, Constanza González y Saida Moraga.

Si la API cambia de IP al usar distinta red WiFi, puede configurar la base URL en BuildConfig o en un archivo local:
private const val BASE_URL = "http://TU_IP:PUERTO/"

---

## 🚀 Características principales

- Navegación con **Navigation Compose**
- Manejo de estados con **ViewModel**
- Llamadas a API usando **Retrofit + Gson**
- Carga de imágenes con **Coil**
- Uso de **Corrutinas** para tareas asíncronas
- Uso opcional de **OpenStreetMap (osmdroid)**
- Pruebas unitarias con **JUnit 5**, **Kotest** y **MockK**
- Generación de APK firmada con **keystore**

---

## 📂 Estructura del proyecto
app/
├── src/
│ ├── main/
│ │ ├── java/com/example/rosapastelapp/
│ │ ├── res/
│ │ └── AndroidManifest.xml
│ └── test/ (JUnit 5 + Kotest)
│
└── build.gradle.kts

---

## 🔧 Tecnologías y librerías

- **Kotlin**
- **Jetpack Compose**
- **Navigation Compose**
- **ViewModel + LiveData**
- **Retrofit 2.11.0**
- **Coil 2.7.0**
- **Osmdroid 6.1.18**
- **Kotest 5.7.2**
- **MockK 1.13.10**
- **JUnit 5.10.5**

---

## 🧪 Pruebas

El proyecto está configurado para usar *solo JUnit 5*:

```kotlin
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

