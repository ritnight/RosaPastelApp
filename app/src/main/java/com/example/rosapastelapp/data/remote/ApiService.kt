package com.example.rosapastelapp.data.remote

import com.example.rosapastelapp.data.model.Producto
import retrofit2.http.GET

interface ApiService {

    @GET("productos")
    suspend fun getProductos(): List<Producto>
}
