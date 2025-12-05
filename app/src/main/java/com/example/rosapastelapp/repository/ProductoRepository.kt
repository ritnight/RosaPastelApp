// com.example.rosapastelapp.repository.ProductoRepository

package com.example.rosapastelapp.repository

import com.example.rosapastelapp.data.model.Producto
import com.example.rosapastelapp.data.remote.ApiService
import com.example.rosapastelapp.data.remote.RetrofitInstance

class ProductoRepository(
    private val api: ApiService = RetrofitInstance.api   // 👈 default = igual que ahora
) {

    suspend fun getProductos(): List<Producto> {
        return api.getProductos()
    }
}

