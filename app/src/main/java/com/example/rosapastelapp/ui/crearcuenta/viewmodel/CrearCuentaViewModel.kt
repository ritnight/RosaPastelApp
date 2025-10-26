package com.example.rosapastelapp.ui.crearcuenta.viewmodel

import androidx.lifecycle.ViewModel
import com.example.rosapastelapp.ui.crearcuenta.model.CrearCuentaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CrearCuentaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CrearCuentaUiState())
    val uiState: StateFlow<CrearCuentaUiState> = _uiState.asStateFlow()

    fun onNombreChange(nombre: String) {
        _uiState.value = _uiState.value.copy(nombre = nombre)
    }

    fun onCorreoChange(correo: String) {
        _uiState.value = _uiState.value.copy(correo = correo)
    }

    fun onDireccionChange(direccion: String) {
        _uiState.value = _uiState.value.copy(direccion = direccion)
    }

    fun onContrasenaChange(contrasena: String) {
        _uiState.value = _uiState.value.copy(contrasena = contrasena)
    }

    fun onRepetirContrasenaChange(repetirContrasena: String) {
        _uiState.value = _uiState.value.copy(repetirContrasena = repetirContrasena)
    }

    fun onIngresarClick() {
        // TODO: Implementar la lógica de creación de cuenta
    }
}
