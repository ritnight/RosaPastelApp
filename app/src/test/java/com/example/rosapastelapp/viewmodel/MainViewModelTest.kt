package com.example.rosapastelapp.viewmodel

import com.example.rosapastelapp.data.model.Producto
import com.example.rosapastelapp.repository.ProductoRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest : StringSpec({

    // 1) cargarProductos llena productos con lo que entrega el repositorio
    "cargarProductos debe llenar el StateFlow con la lista del repositorio" {
        val testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)

        try {
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

            val repoMock = mockk<ProductoRepository>()
            coEvery { repoMock.getProductos() } returns fakeProductos

            val viewModel = MainViewModel(productoRepository = repoMock)

            viewModel.cargarProductos()
            testDispatcher.scheduler.advanceUntilIdle()

            viewModel.productos.value shouldContainExactly fakeProductos
            coVerify(exactly = 1) { repoMock.getProductos() }
        } finally {
            Dispatchers.resetMain()
        }
    }

    // 2) agregarAlCarrito agrega un item nuevo si no existe
    "agregarAlCarrito debe agregar un nuevo item cuando el producto no existe en el carrito" {
        val repoMock = mockk<ProductoRepository>(relaxed = true)
        val viewModel = MainViewModel(productoRepository = repoMock)

        val producto = Producto(
            id = 10,
            nombre = "Delineador",
            descripcion = "Delineador prueba",
            precio = 8990,
            stock = 5,
            imagenUrl = "https://ejemplo.com/delineador.png",
            categoria = "MAQUILLAJE"
        )

        viewModel.agregarAlCarrito(producto, cantidad = 2)

        val carrito = viewModel.carrito.value
        carrito.shouldHaveSize(1)
        carrito[0].producto shouldBe producto
        carrito[0].cantidad shouldBe 2
    }

    // 3) agregarAlCarrito suma cantidad si el producto ya está en el carrito
    "agregarAlCarrito debe sumar la cantidad si el producto ya existe en el carrito" {
        val repoMock = mockk<ProductoRepository>(relaxed = true)
        val viewModel = MainViewModel(productoRepository = repoMock)

        val producto = Producto(
            id = 11,
            nombre = "Base de maquillaje",
            descripcion = "Base prueba",
            precio = 12990,
            stock = 10,
            imagenUrl = "https://ejemplo.com/base.png",
            categoria = "MAQUILLAJE"
        )

        viewModel.agregarAlCarrito(producto, cantidad = 1)
        viewModel.agregarAlCarrito(producto, cantidad = 3)

        val carrito = viewModel.carrito.value
        carrito.shouldHaveSize(1)
        carrito[0].producto shouldBe producto
        carrito[0].cantidad shouldBe 4
    }

    // 4) actualizarCantidad cambia la cantidad de un producto
    "actualizarCantidad debe modificar la cantidad de un producto del carrito" {
        val repoMock = mockk<ProductoRepository>(relaxed = true)
        val viewModel = MainViewModel(productoRepository = repoMock)

        val producto = Producto(
            id = 20,
            nombre = "Crema Hidratante",
            descripcion = "Crema prueba",
            precio = 5990,
            stock = 8,
            imagenUrl = "https://ejemplo.com/crema2.png",
            categoria = "SKINCARE"
        )

        viewModel.agregarAlCarrito(producto, cantidad = 1)

        val productoIdInt = producto.id?.toInt() ?: 0
        viewModel.actualizarCantidad(productoId = productoIdInt, nuevaCantidad = 5)

        val carrito = viewModel.carrito.value
        carrito.shouldHaveSize(1)
        carrito[0].cantidad shouldBe 5
    }

    // 5) actualizarCantidad con 0 deja el carrito sin ese producto
    "actualizarCantidad con cantidad 0 debe eliminar el producto del carrito" {
        val repoMock = mockk<ProductoRepository>(relaxed = true)
        val viewModel = MainViewModel(productoRepository = repoMock)

        val producto = Producto(
            id = 30,
            nombre = "Serum Facial",
            descripcion = "Serum prueba",
            precio = 15990,
            stock = 4,
            imagenUrl = "https://ejemplo.com/serum.png",
            categoria = "SKINCARE"
        )

        viewModel.agregarAlCarrito(producto, cantidad = 2)

        val productoIdInt = producto.id?.toInt() ?: 0
        viewModel.actualizarCantidad(productoId = productoIdInt, nuevaCantidad = 0)

        val carrito = viewModel.carrito.value
        carrito.shouldHaveSize(0)
    }
})

