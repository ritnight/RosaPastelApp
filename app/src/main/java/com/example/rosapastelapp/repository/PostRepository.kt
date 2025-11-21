package com.example.rosapastelapp.repository

import com.example.rosapastelapp.data.model.Post
import com.example.rosapastelapp.data.remote.RetrofitInstance

// Este repositorio se encarga de acceder a los datos usando Retrofit
class PostRepository {

    // Función que obtiene los posts desde la API
    suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }
}
