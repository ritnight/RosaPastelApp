package com.example.rosapastelapp

import com.example.rosapastelapp.data.model.Producto
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductoTest {

    @Test
    fun crearProducto_asignaValoresCorrectamente() {
        // 2. DATOS DE PRUEBA
        // Usamos '1L' porque tu modelo pide un Long, no un Int.
        val idEsperado = 1L
        val nombreEsperado = "Pastel de Fresa"
        val precioEsperado = 5000
        val categoriaEsperada = "Pasteles"

        // 3. CREAR EL PRODUCTO
        // Si 'Producto' sale en rojo, verifica el 'import' de arriba
        val producto = Producto(
            id = idEsperado,
            nombre = nombreEsperado,
            descripcion = "Delicioso pastel con crema",
            precio = precioEsperado,
            stock = 10,
            imagenUrl = "http://imagen.com/pastel.jpg",
            categoria = categoriaEsperada
        )

        // 4. VERIFICACIONES
        assertEquals(idEsperado, producto.id)
        assertEquals(nombreEsperado, producto.nombre)
        assertEquals(precioEsperado, producto.precio)
        assertEquals(categoriaEsperada, producto.categoria)
    }
}