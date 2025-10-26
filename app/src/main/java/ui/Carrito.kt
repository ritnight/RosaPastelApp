package com.example.rosapastelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rosapastelapp.R
import com.example.rosapastelapp.ui.theme.Cordovan // Importa tu color Cordovan
import com.example.rosapastelapp.ui.theme.NewYorkPink // Importa tu color NewYorkPink

// --- Data Class para el Producto ---
// (Define esto en un archivo separado, por ejemplo, model/Product.kt)
data class CartProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val imageRes: Int
)

// --- Lista de Productos de Prueba ---
// (Esto debería venir de tu ViewModel o repositorio)
val dummyCartList = listOf(
    CartProduct(1, "Corrector True SKIN HIGH COVER", "¡No hay nada más multifacético...", "$5.990", R.drawable.product_corrector),
    CartProduct(2, "Tinte Para Labios Y Mejillas What A Tint!", "Este tinte de labios y mejillas...", "$4.990", R.drawable.product_tint),
    CartProduct(3, "Iluminador More Than Glow", "La textura sedosa, ultra suave...", "$5.990", R.drawable.product_iluminador),
    CartProduct(4, "Ampolla Calmante Centella 100Ml", "SKIN1004 utiliza el poder...", "$29.990", R.drawable.product_ampolla)
)

// --- Pantalla Principal del Carrito ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen() {
    Scaffold(
        topBar = { CartTopBar() },
        bottomBar = { CartCheckoutBar(total = "$46.870") }
    ) { innerPadding ->

        // Columna perezosa (LazyColumn) para la lista de productos
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp), // Espacio entre items
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(dummyCartList) { product ->
                CartItem(product = product)
            }
        }
    }
}

// --- Componentes del Carrito ---

@Composable
fun CartItem(product: CartProduct) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        // Columna 1: Imagen y Selector de Cantidad
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.25f)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            QuantitySelectorCart()
        }

        // Columna 2: Título y Descripción
        Column(
            modifier = Modifier
                .weight(0.5f)
                .padding(horizontal = 8.dp)
        ) {
            Text(product.name, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), fontSize = 15.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(product.description, style = MaterialTheme.typography.bodySmall, color = Color.Gray, fontSize = 13.sp)
        }

        // Columna 3: Eliminar y Precio
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.weight(0.25f)
        ) {
            // Botón Eliminar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { /* Lógica Eliminar */ }
            ) {
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = "Eliminar",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
                Text("Eliminar", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(40.dp)) // Espaciador para empujar el precio hacia abajo

            Text(product.price, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
        }
    }
}

@Composable
fun QuantitySelectorCart() {
    // Simulación del selector de cantidad
    Row(
        modifier = Modifier
            .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("1", style = MaterialTheme.typography.