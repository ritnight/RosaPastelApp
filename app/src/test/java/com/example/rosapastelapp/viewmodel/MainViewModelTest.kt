package com.example.rosapastelapp.viewmodel

import com.example.rosapastelapp.data.model.Producto
import com.example.rosapastelapp.repository.ProductoRepository
import com.example.rosapastelapp.viewmodel.MainViewModel

// Kotest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

// MockK
import io.mockk.coEvery
import io.mockk.mockk

// Coroutines Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest : StringSpec({

    "cargarProductos debe llenar el StateFlow con la lista del repositorio" {
        // 1. Preparamos datos falsos
        val fakeProductos = listOf(
            Producto(
                id = 1,
                nombre = "Labial Rosa",
                descripcion = "Labial de prueba",
                precio = 4990.0,   // ajusta a Int o Double según tu data class
                stock = 5,
                imagenUrl = "https://ejemplo.com/labial.png",
                categoria = "MAQUILLAJE"
            ),
            Producto(
                id = 2,
                nombre = "Crema Facial",
                descripcion = "Crema de prueba",
                precio = 7990.0,
                stock = 3,
                imagenUrl = "https://ejemplo.com/crema.png",
                categoria = "SKINCARE"
            )
        )

        // 2. Mock del repositorio
        val repoMock = mockk<ProductoRepository>()
        coEvery { repoMock.getProductos() } returns fakeProductos

        // 3. ViewModel usando el repo falso
        val viewModel = MainViewModel(productoRepository = repoMock)

        // 4. Ejecutamos la corrutina de carga
        runTest {
            viewModel.cargarProductos()
        }

        // 5. Verificamos que el StateFlow tenga nuestra lista
        viewModel.productos.value shouldContainExactly fakeProductos
    }

    "seleccionarProducto debe guardar el producto seleccionado" {
        val producto = Producto(
            id = 10,
            nombre = "Delineador Epic Ink",
            descripcion = "Delineador de ojos NYX",
            precio = 8990.0,
            stock = 2,
            imagenUrl = "https://ejemplo.com/delineador.png",
            categoria = "MAQUILLAJE"
        )

        val repoMock = mockk<ProductoRepository>()
        val viewModel = MainViewModel(productoRepository = repoMock)

        viewModel.seleccionarProducto(producto)

        viewModel.productoSeleccionado.value shouldBe producto
    }
})
