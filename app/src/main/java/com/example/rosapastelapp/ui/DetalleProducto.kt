package com.example.rosapastelapp.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rosapastelapp.data.model.Producto
import com.example.rosapastelapp.navigation.Screen
import com.example.rosapastelapp.ui.theme.Cordovan
import com.example.rosapastelapp.ui.theme.NewYorkPink
import com.example.rosapastelapp.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleProducto(viewModel: MainViewModel) {
    val context = LocalContext.current
    val productoSeleccionado by viewModel.productoSeleccionado.collectAsState()

    var quantity by remember { mutableIntStateOf(1) }

    // Si no hay producto seleccionado (por ejemplo, se llegó directo)
    if (productoSeleccionado == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No se encontró el producto seleccionado")
        }
        return
    }

    val producto = productoSeleccionado!!

    Scaffold(
        topBar = { TopBarDetalleProducto(viewModel) },
        bottomBar = {
            BottomNavBarPrincipal(
                viewModel = viewModel,
                itemSeleccionado = "Home"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ProductImageAndControls(producto = producto)

            Spacer(modifier = Modifier.height(16.dp))

            ProductInfoSection(
                title = producto.nombre,
                price = "$${producto.precio}",
                brand = producto.categoria,
                description = producto.descripcion
            )

            Spacer(modifier = Modifier.height(16.dp))


            Spacer(modifier = Modifier.height(16.dp))

            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { quantity = it }
            )

            // BOTÓN AÑADIR AL CARRITO
            Button(
                onClick = {
                    viewModel.agregarAlCarrito(producto, quantity)
                    Toast.makeText(
                        context,
                        "Añadido al carrito: ${producto.nombre} x$quantity",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NewYorkPink)
            ) {
                Text(
                    "Añadir al carrito",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarDetalleProducto(viewModel: MainViewModel) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { viewModel.navigateTo(Screen.MainScreen) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Cordovan
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
private fun ProductImageAndControls(producto: Producto) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = producto.imagenUrl,
            contentDescription = producto.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            contentScale = ContentScale.Fit
        )

        Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Favorito",
            tint = Cordovan,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(32.dp)
        )
    }
}


@Composable
fun ProductInfoSection(
    title: String,
    price: String,
    brand: String,
    description: String
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                price,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }

        Text(
            brand,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        Row(
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            repeat(5) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Descripción:",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )

        Text(
            text = if (description.isNotBlank()) description
            else "Sin descripción disponible.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }


@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        OutlinedButton(
            onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
            modifier = Modifier.size(36.dp),
            contentPadding = PaddingValues(0.dp),
            border = null
        ) {
            Text("-", fontSize = 18.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.width(24.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedButton(
            onClick = { onQuantityChange(quantity + 1) },
            modifier = Modifier.size(36.dp),
            contentPadding = PaddingValues(0.dp),
            border = null
        ) {
            Text("+", fontSize = 18.sp, color = Color.Black)
        }
    }
}

@Composable
private fun BottomNavBarPrincipal(
    viewModel: MainViewModel,
    itemSeleccionado: String
) {
    NavigationBar(
        containerColor = RosaFondoNav,
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            selected = itemSeleccionado == "Home",
            onClick = { viewModel.navigateTo(Screen.MainScreen) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio",
                    modifier = Modifier.size(if (itemSeleccionado == "Home") 36.dp else 28.dp)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NewYorkPink,
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = itemSeleccionado == "Profile",
            onClick = { viewModel.navigateTo(Screen.Profile) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Perfil",
                    modifier = Modifier.size(if (itemSeleccionado == "Profile") 36.dp else 28.dp)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NewYorkPink,
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = itemSeleccionado == "Favorites",
            onClick = { viewModel.navigateTo(Screen.Favorites) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "Favoritos",
                    modifier = Modifier.size(if (itemSeleccionado == "Favorites") 36.dp else 28.dp)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NewYorkPink,
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetalleProductoPreview() {
    DetalleProducto(viewModel = MainViewModel())
}
