# 🌸 RosaPastelApp 🎨📱

Aplicación móvil desarrollada en **Android Studio** utilizando **Kotlin** y **Jetpack Compose**.  
Este proyecto forma parte del ramo **Desarrollo de Aplicaciones Móviles**.

---

## 👩‍💻 Integrantes del equipo

- Alejandra González  
- Constanza González  
- Saida Moraga  

---

## 📌 Descripción general

RosaPastelApp es una aplicación de e-commerce orientada a productos de **maquillaje** y **skincare**.  
La aplicación consume una **API REST externa** desarrollada en backend y permite visualizar productos, ver detalles y manejar un **carrito de compras**.

El proyecto cumple con los requisitos de:
- ✅ Consumo de API externa
- ✅ Implementación de CRUD
- ✅ Pruebas unitarias
- ✅ Generación de APK firmada

---

## 🚀 Características principales

- 🧭 Navegación con **Navigation Compose**
- 🏗️ Arquitectura **MVVM**
- 📊 Manejo de estado con **ViewModel** y **StateFlow**
- 🌐 Consumo de API REST usando **Retrofit + Gson**
- 🖼️ Carga de imágenes desde URL con **Coil**
- ⚡ Corrutinas para operaciones asíncronas
- 🛒 Carrito de compras (CRUD en frontend)
- 🧪 Pruebas unitarias con **JUnit 5**, **Kotest** y **MockK**
- 📦 Generación de **APK firmada con keystore**

---

## 🔧 Requisitos previos

- **Android Studio**: Hedgehog o superior
- **Kotlin**: 1.9.0 o superior
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34
- **JDK**: 17

---

## 📥 Instalación y configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/RosaPastelApp.git
cd RosaPastelApp
```

### 2. Abrir en Android Studio
- Abre Android Studio
- Selecciona "Open an existing project"
- Navega hasta la carpeta del proyecto y selecciónala

### 3. Configurar la API
La aplicación consume una API REST externa. Si la IP cambia (por ejemplo, al cambiar de red WiFi), debes actualizar la **base URL** en el archivo de configuración:

**Ubicación**: `app/src/main/java/com/example/rosapastelapp/data/repository/ApiService.kt`

```kotlin
private const val BASE_URL = "http://TU_IP:PUERTO/"
```

**Ejemplo**:
```kotlin
private const val BASE_URL = "http://192.168.1.100:8080/"
```

### 4. Sincronizar dependencias
Android Studio sincronizará automáticamente las dependencias de Gradle. Si no lo hace, ejecuta:
```bash
./gradlew build
```

### 5. Ejecutar la aplicación
- Conecta un dispositivo Android o inicia un emulador
- Presiona el botón **Run** (▶️) en Android Studio

---

## 🧱 Arquitectura

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)**:

- **UI (View)**: Pantallas construidas con Jetpack Compose  
- **ViewModel**: Manejo de estado y lógica de presentación  
- **Repository**: Comunicación con la API y manejo de datos  
- **Model**: Clases de datos que representan entidades del backend  

### Flujo de datos:
```
UI → ViewModel → Repository → API
```

---

## 📂 Estructura del proyecto

```text
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/rosapastelapp/
│   │   │   ├── data/
│   │   │   │   ├── model/          # Modelos de datos (Product, CartItem, etc.)
│   │   │   │   └── repository/     # ApiService
│   │   │   ├── navigation/         # NavHost y rutas de navegación
│   │   │   ├── ui/                 # Pantallas Composables
│   │   │   │   ├── screens/        # HomeScreen
│   │   │   │   └── components/     # Componentes reutilizables
│   │   │   └── viewmodel/          # MainViewModel
│   │   ├── res/
│   │   │   ├── drawable/           # Iconos e imágenes
│   │   │   ├── values/             # Strings, colores, temas
│   │   │   └── xml/                # Configuraciones adicionales
│   │   └── AndroidManifest.xml
│   │
│   └── test/
│       └── java/com/example/rosapastelapp/
│           └── viewmodel/
│               └── MainViewModelTest.kt  # Pruebas unitarias
│
├── build.gradle.kts                # Configuración del módulo app
└── gradle/                         # Configuración de Gradle
```

---

## 🌐 API - Endpoints utilizados

La aplicación consume los siguientes endpoints:

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/productos` | Obtiene lista de todos los productos |
| GET | `/productos/{id}` | Obtiene detalle de un producto específico |
| GET | `/productos/categoria/{categoria}` | Filtra productos por categoría |

---

## 🛒 Carrito de compras

El carrito de compras se gestiona completamente utilizando `StateFlow` dentro del `MainViewModel`.

### Operaciones CRUD:
- **Crear**: Agregar producto al carrito
- **Leer**: Listar productos del carrito
- **Actualizar**: Cambiar cantidad de un producto
- **Eliminar**: Quitar producto del carrito

### Ejemplo de uso:
```kotlin
// Agregar al carrito
viewModel.addToCart(product, quantity = 1)

// Ver carrito
val cartItems by viewModel.cartItems.collectAsState()

// Actualizar cantidad
viewModel.updateCartItemQuantity(productId, newQuantity)

// Eliminar del carrito
viewModel.removeFromCart(productId)
```

---

## 🔧 Tecnologías y librerías utilizadas

### Core
- **Kotlin** 1.9.0
- **Jetpack Compose** 1.5.4
- **Material 3**

### Arquitectura y navegación
- **Navigation Compose** 2.7.5
- **ViewModel** 2.6.2
- **StateFlow** (Kotlin Coroutines)

### Networking
- **Retrofit** 2.11.0
- **Gson Converter** 2.11.0
- **OkHttp** 4.12.0

### Imágenes
- **Coil Compose** 2.7.0

### Pruebas
- **JUnit 5** 5.10.5
- **Kotest** 5.7.2
- **MockK** 1.13.10
- **Coroutines Test** 1.7.3

---

## 🧪 Pruebas unitarias

El proyecto utiliza **JUnit 5** como framework principal de pruebas.

### Configuración en `build.gradle.kts`:
```kotlin
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.5")
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
    testImplementation("io.mockk:mockk:1.13.10")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}
```

### Pruebas implementadas:
- ✅ Agregar producto al carrito
- ✅ Actualizar cantidad en el carrito
- ✅ Eliminar producto del carrito
- ✅ Calcular total del carrito
- ✅ Cargar productos desde la API

### Ejecutar las pruebas:
**Desde Android Studio**:
- Click derecho en la carpeta `test`
- Selecciona "Run Tests"

**Desde terminal**:
```bash
./gradlew test
```

---

## 📦 Generación de APK firmada

### Pasos realizados:

1. **Crear keystore**:
```bash
keytool -genkey -v -keystore rosapastel.jks -keyalg RSA -keysize 2048 -validity 10000 -alias rosapastel
```

2. **Configurar firma en `build.gradle.kts`**:
```kotlin
android {
    signingConfigs {
        create("release") {
            storeFile = file("../rosapastel.jks")
            storePassword = "tu_password"
            keyAlias = "rosapastel"
            keyPassword = "tu_password"
        }
    }
    
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}
```

3. **Generar APK**:
   - `Build` → `Generate Signed Bundle / APK`
   - Seleccionar `APK`
   - Elegir el keystore y completar credenciales
   - Seleccionar variante `release`

4. **Ubicación del APK**:
```
app/release/app-release.apk
```

---

## ✅ Requisitos académicos cumplidos

| Requisito | Estado |
|-----------|--------|
| Consumo de API externa | ✅ Implementado con Retrofit |
| CRUD completo | ✅ Carrito de compras (frontend) |
| Pruebas unitarias (mínimo 5) | ✅ 5+ pruebas con JUnit 5 |
| APK generada y firmada | ✅ Keystore creado y APK firmada |
| Arquitectura MVVM | ✅ ViewModels + Repository |
| Uso de tecnologías modernas | ✅ Jetpack Compose, Kotlin Coroutines |

---

## 📱 Estado del proyecto

✅ Proyecto **funcional y completo**  
✅ Probado en entorno local  
✅ Listo para evaluación académica  

---

## 🎓 Información académica

**Asignatura**: Desarrollo de Aplicaciones Móviles  
**Entrega**: Examen Transversal  
**Institución**: Duoc UC
**Fecha**: 18 de diciembre de 2025

---

## 📄 Licencia

Este proyecto es de uso académico y fue desarrollado como parte de la evaluación del ramo.

---

**Desarrollado con ❤️ por el equipo RosaPastelApp**
