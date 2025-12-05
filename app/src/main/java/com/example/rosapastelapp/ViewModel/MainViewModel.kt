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

class MainViewModel : ViewModel() {

    // ---------- Navegación ----------
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

    // ---------- Productos (backend) ----------
    private val productoRepository = ProductoRepository()

    // Estado interno mutable
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())

    // Estado expuesto solo-lectura a las pantallas
    val productos: StateFlow<List<Producto>> = _productos

    // arriba de cargarProductos()
    private val _productoSeleccionado = MutableStateFlow<Producto?>(null)
    val productoSeleccionado: StateFlow<Producto?> = _productoSeleccionado

    fun seleccionarProducto(producto: Producto) {
        _productoSeleccionado.value = producto
    }
    // Llama al backend (GET /api/productos) y actualiza el estado.
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
}
