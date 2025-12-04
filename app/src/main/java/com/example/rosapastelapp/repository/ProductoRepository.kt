package com.example.rosapastelapp.repository

import com.example.rosapastelapp.data.model.Producto
import com.example.rosapastelapp.data.remote.RetrofitInstance

// Capa que se encarga de hablar con la API para todo lo relacionado a productos
class ProductoRepository {

    // Llama al endpoint GET /api/productos del backend
    suspend fun getProductos(): List<Producto> {
        return RetrofitInstance.api.getProductos()
    }
}
