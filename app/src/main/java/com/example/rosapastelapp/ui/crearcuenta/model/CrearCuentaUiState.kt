package com.example.rosapastelapp.ui.crearcuenta.model

data class CrearCuentaUiState(
    val nombre: String = "",
    val correo: String = "",
    val direccion: String = "",
    val contrasena: String = "",
    val repetirContrasena: String = "",
    val errores: CrearCuentaErrores = CrearCuentaErrores()
)

data class CrearCuentaErrores(
    val nombre: String? = null,
    val correo: String? = null,
    val direccion: String? = null,
    val contrasena: String? = null,
    val repetirContrasena: String? = null
)
