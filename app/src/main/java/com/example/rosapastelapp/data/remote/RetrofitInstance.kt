package com.example.rosapastelapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "192.168.100.3:8080/api/"


    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)                          // URL base de tu backend
            .addConverterFactory(GsonConverterFactory.create()) // convierte JSON a data class
            .build()
            .create(ApiService::class.java)             // genera la implementación de ApiService
    }
}
