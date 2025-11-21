package com.example.rosapastelapp.data.model

// Representa un post obtenido desde la API
data class Post(
    val userId: Int, // ID del usuario que creó el post
    val id: Int,     // ID del post
    val title: String, // Título
    val body: String   // Contenido
)
