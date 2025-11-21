package com.example.rosapastelapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton que contiene la configuración de Retrofit
object RetrofitInstance {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // Se instancia el servicio de la API una sola vez
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // URL base de la API
            .addConverterFactory(GsonConverterFactory.create()) // Conversor JSON
            .build()
            .create(ApiService::class.java) // Implementa la interfaz ApiService
    }
}
