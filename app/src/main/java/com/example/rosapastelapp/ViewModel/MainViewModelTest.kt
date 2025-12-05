package com.example.rosapastelapp.viewmodel

import com.example.rosapastelapp.data.model.Producto
import com.example.rosapastelapp.repository.ProductoRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.coEvery
import io.mockk.mockk
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
                precio = 4990,
                stock = 5,
                imagenUrl = "https://ejemplo.com/labial.png",
                categoria = "MAQUILLAJE"
            ),
            Producto(
                id = 2,
                nombre = "Crema Facial",
                descripcion = "Crema de prueba",
                precio = 7990,
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

            // 5. Verificamos que el StateFlow tenga nuestra lista
            viewModel.productos.value shouldContainExactly fakeProductos
        }
    }
})
