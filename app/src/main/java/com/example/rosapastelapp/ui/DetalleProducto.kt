package com.example.rosapastelapp.ui // Ajusta tu paquete si es necesario

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rosapastelapp.R // Asegúrate de tener las imágenes y colores
import com.example.rosapastelapp.ui.theme.NewYorkPink
import com.example.rosapastelapp.viewmodel.MainViewModel

/**
 * Composable que representa la pantalla de detalle de un producto.
 */
@Composable
fun ProductDetailScreen(viewModel: MainViewModel) {
    // Estado para la cantidad del producto
    var quantity by remember { mutableIntStateOf(1) }
    // Estado para el color seleccionado (usamos el color vino tinto por defecto)
    var selectedColor by remember { mutableStateOf(Color(0xFF8B002B)) } // Vino tinto (Cordovan similar)
    val NewYorkPink = Color(0xFFd87e8b)
    Scaffold(
        bottomBar = { ProductBottomBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Sección superior de Imagen y Controles
            ProductImageAndControls(
                productImageRes = R.drawable.tinta_essence, // Reemplaza con tu imagen de producto
                onBackClicked = { /* Lógica de navegación hacia atrás */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Detalles del Producto (Título, Precio, Descripción)
            ProductInfoSection(
                title = "What a Tint!",
                price = "$4.990",
                brand = "ESSENCE"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Selector de Color
            ColorSelector(
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Selector de Cantidad
            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { quantity = it }
            )

            // Botón Añadir al Carrito
            Button(
                onClick = { /* Lógica de añadir al carrito */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NewYorkPink) // Usando tu color rosa
            ) {
                Text("Añadir al carrito", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// --- Componentes Reutilizables ---

@Composable
fun ProductImageAndControls(
    productImageRes: Int,
    onBackClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp) // Altura para mostrar ambas partes de la imagen
    ) {
        // La imagen principal del producto (izquierda)
        Image(
            painter = painterResource(id = productImageRes),
            contentDescription = "Lip and Cheek Tint Product",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.55f) // Simula el ancho de la imagen izquierda
                .align(Alignment.CenterStart)
                .fillMaxHeight()
        )

        // El aplicador (derecha) - Simplemente un fondo blanco con el texto
        Box(
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .background(Color.White)
        ) {
            // El círculo rosa y el texto invertido
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(NewYorkPink)
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "FOR\nLIPS &\nCHEEKS",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    // No hay una forma directa de invertir en Compose,
                    // esto es una simplificación visual.
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Flecha de retroceso (arriba izquierda)
        IconButton(
            onClick = onBackClicked,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
        }

        // Icono de corazón (favorito) (derecha media)
        Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
                .size(40.dp)
        )
    }
}

@Composable
fun ProductInfoSection(title: String, price: String, brand: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
            Text(price, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold))
        }

        Text(brand, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)

        // Estrellas de Valoración (5 Estrellas completas en la imagen)
        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            repeat(5) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Rating Star",
                    tint = Color(0xFFFFC107), // Color amarillo dorado
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Descripción del producto
        Text(
            text = "Tinte para labios y mejillas. Su textura no pegajosa, similar al agua, proporciona un tono suave con un acabado natural.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun ColorSelector(selectedColor: Color, onColorSelected: (Color) -> Unit) {
    val colors = listOf(Color(0xFF8B002B), Color(0xFFE91E63)) // Vino tinto y Rosa Brillante

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("Color", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            colors.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(color)
                        .clickable { onColorSelected(color) }
                ) {
                    if (color == selectedColor) {
                        // Borde de selección para indicar el color elegido
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(Color.Transparent)
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.5f))
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuantitySelector(quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 16.dp)
    ) {
        // Botón - (Disminuir)
        OutlinedButton(
            onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
            modifier = Modifier.size(36.dp),
            contentPadding = PaddingValues(0.dp),
            border = null // Quitar el borde si es necesario
        ) {
            Text("-", fontSize = 18.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Cantidad Actual
        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.width(24.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Botón + (Aumentar)
        OutlinedButton(
            onClick = { onQuantityChange(quantity + 1) },
            modifier = Modifier.size(36.dp),
            contentPadding = PaddingValues(0.dp),
            border = null // Quitar el borde si es necesario
        ) {
            Text("+", fontSize = 18.sp, color = Color.Black)
        }
    }
}

@Composable
fun ProductBottomBar() {
    // Barra de navegación inferior simple (Home, Perfil, Favoritos)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F0F0)) // Un color de fondo gris claro para la barra
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // Icono Home
        Icon(
            painter = painterResource(id = R.drawable.ic_home), // Reemplaza con tu icono
            contentDescription = "Home",
            modifier = Modifier.size(30.dp).clickable { /* Nav Home */ }
        )
        // Icono Perfil
        Icon(
            painter = painterResource(id = R.drawable.ic_monito), // Reemplaza con tu icono
            contentDescription = "Profile",
            tint = Color.Gray,
            modifier = Modifier.size(30.dp).clickable { /* Nav Profile */ }
        )
        // Icono Corazón (Favoritos)
        Icon(
            painter = painterResource(id = R.drawable.ic_corazon), // Reemplaza con tu icono
            contentDescription = "Favorites",
            modifier = Modifier.size(30.dp).clickable { /* Nav Favorites */ }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductDetailScreenPreview() {
    ProductDetailScreen()
}