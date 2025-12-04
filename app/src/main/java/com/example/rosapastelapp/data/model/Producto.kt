package com.example.rosapastelapp.data.model

// Modelo que representa un producto tal como lo envía el backend
data class Producto(
    val id: Long?,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val stock: Int,
    val imagenUrl: String,
    val categoria: String
)
