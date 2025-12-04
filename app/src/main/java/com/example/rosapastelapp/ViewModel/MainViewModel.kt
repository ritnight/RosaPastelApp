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

    private val productoRepository = ProductoRepository()

    // Estado interno mutable
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    // Estado expuesto solo-lectura a las pantallas
    val productos: StateFlow<List<Producto>> = _productos


    //llama al backend (GET /api/productos) y actualiza el estado.

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                val lista = productoRepository.getProductos()
                _productos.value = lista
            } catch (e: Exception) {
                _productos.value = emptyList()
            }
        }
    }
}
