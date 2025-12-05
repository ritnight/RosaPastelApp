package com.example.rosapastelapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rosapastelapp.data.model.Producto
import com.example.rosapastelapp.navigation.NavigationEvent
import com.example.rosapastelapp.navigation.Screen
import com.example.rosapastelapp.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

// Para el carrito
data class CartItem(
    val producto: Producto,
    val cantidad: Int
)

class MainViewModel(
    private val productoRepository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    //  NAVEGACIÓN
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()

    fun navigateTo(screen: Screen) {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateTo(route = screen))
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.PopBackStack)
        }
    }

    //  LISTA DE PRODUCTOS DEL BACKEND
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                val lista = productoRepository.getProductos()
                println("DEBUG getProductos: recibidos = ${lista.size}")
                _productos.value = lista
            } catch (e: Exception) {
                println("ERROR getProductos: ${e.message}")
                e.printStackTrace()
                _productos.value = emptyList()
            }
        }
    }

    //  PRODUCTO SELECCIONADO PARA DETALLE
    private val _productoSeleccionado = MutableStateFlow<Producto?>(null)
    val productoSeleccionado: StateFlow<Producto?> = _productoSeleccionado

    fun seleccionarProducto(producto: Producto) {
        _productoSeleccionado.value = producto
    }

    //  CARRITO
    private val _carrito = MutableStateFlow<List<CartItem>>(emptyList())
    val carrito: StateFlow<List<CartItem>> = _carrito

    fun agregarAlCarrito(producto: Producto, cantidad: Int) {
        val actual = _carrito.value.toMutableList()
        val index = actual.indexOfFirst { it.producto.id == producto.id }

        if (index >= 0) {
            // Ya existe: suma cantidades
            val itemViejo = actual[index]
            actual[index] = itemViejo.copy(cantidad = itemViejo.cantidad + cantidad)
        } else {
            // No existe: agrega nuevo
            actual.add(CartItem(producto = producto, cantidad = cantidad))
        }

        _carrito.value = actual
    }

    fun actualizarCantidad(productoId: Int, nuevaCantidad: Int) {
        val actual = _carrito.value.toMutableList()

        val index = actual.indexOfFirst {
            (it.producto.id ?: 0L).toInt() == productoId
        }

        if (index >= 0) {
            if (nuevaCantidad <= 0) {
                actual.removeAt(index)
            } else {
                actual[index] = actual[index].copy(cantidad = nuevaCantidad)
            }
            _carrito.value = actual
        }
    }

    fun eliminarDelCarrito(productoId: Int) {
        _carrito.value = _carrito.value.filterNot {
            (it.producto.id ?: 0L).toInt() == productoId
        }
    }
}
