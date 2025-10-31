package com.example.rosapastelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rosapastelapp.R
import com.example.rosapastelapp.navigation.Screen
import com.example.rosapastelapp.ui.theme.Cordovan
import com.example.rosapastelapp.ui.theme.NewYorkPink
import com.example.rosapastelapp.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleProducto(viewModel: MainViewModel) {
    var quantity by remember { mutableIntStateOf(1) }
    var selectedColor by remember { mutableStateOf(Color(0xFF8B002B)) }

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
        ) {
            ProductImageAndControls(
                productImageRes = R.drawable.tinta_essence,
                onBackClicked = { viewModel.navigateTo(Screen.MainScreen) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProductInfoSection(
                title = "What a Tint!",
                price = "$4.990",
                brand = "ESSENCE"
            )

            Spacer(modifier = Modifier.height(16.dp))

            ColorSelector(
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { quantity = it }
            )

            Button(
                onClick = { /* acción futura */ },
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
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
private fun ProductImageAndControls(
    productImageRes: Int,
    onBackClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        Image(
            painter = painterResource(id = productImageRes),
            contentDescription = "Producto",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .align(Alignment.CenterStart)
                .fillMaxHeight()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .background(Color.White)
        ) {
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
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Favorito",
            tint = Cordovan,
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

        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            repeat(5) {
                Icon(
                    imageVector = Icons.Filled.Star, // Se usa imageVector en lugar de painter
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Text(
            text = "Tinte para labios y mejillas. Su textura no pegajosa, similar al agua, proporciona un tono suave con un acabado natural.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun ColorSelector(selectedColor: Color, onColorSelected: (Color) -> Unit) {
    val colors = listOf(Color(0xFF8B002B), Color(0xFFE91E63))

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
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.4f))
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
