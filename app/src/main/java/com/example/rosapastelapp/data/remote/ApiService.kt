package com.example.rosapastelapp.data.remote

import com.example.rosapastelapp.data.model.Post
import retrofit2.http.GET

// Define los endpoints HTTP de la API
interface ApiService {

    //define una solicitud GET para obtener posts
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
