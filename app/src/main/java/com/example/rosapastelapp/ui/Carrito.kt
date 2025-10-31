package com.example.rosapastelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rosapastelapp.R
import com.example.rosapastelapp.ui.theme.Cordovan
import com.example.rosapastelapp.ui.theme.NewYorkPink
import com.example.rosapastelapp.ui.theme.BabyPink
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme
import com.example.rosapastelapp.viewmodel.MainViewModel
import com.example.rosapastelapp.navigation.Screen

data class CartProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val imageRes: Int
)

val dummyCartList = listOf(
    CartProduct(1, "Corrector True SKIN HIGH COVER", "¡No hay nada más multifacético...", "$5.990", R.drawable.corrector),
    CartProduct(2, "Tinte Para Labios Y Mejillas What A Tint!", "Este tinte de labios y mejillas...", "$4.990", R.drawable.tinta_essence),
    CartProduct(3, "Iluminador More Than Glow", "La textura sedosa, ultra suave...", "$5.990", R.drawable.iluminador),
    CartProduct(4, "Ampolla Calmante Centella 100Ml", "SKIN1004 utiliza el poder...", "$29.990", R.drawable.ampolla)
)


// BARRA SUPERIOR (TOP BAR)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopBar(viewModel: MainViewModel) {
    CenterAlignedTopAppBar(
        title = {
            Text("Carrito de Compras", fontWeight = FontWeight.Bold, color = Cordovan)
        },
        navigationIcon = {
            IconButton(onClick = {viewModel.navigateTo(Screen.MainScreen) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Cordovan
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}


// BARRA INFERIOR (CHECKOUT
@Composable
fun CartCheckoutBar(total: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Total:", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text(total, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold), color = Cordovan)
        }

        Button(
            onClick = { /* Acción de Checkout */ },
            modifier = Modifier.height(50.dp).weight(1f).padding(start = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NewYorkPink)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Pagar", modifier = Modifier.size(20.dp).padding(end = 4.dp))
                Text("Pagar Ahora", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold))
            }
        }
    }
}


// BARRA DE NAVEGACIÓN INFERIOR (COMPARTIDA)

@Composable
private fun BottomNavBarPrincipal(
    viewModel: MainViewModel,
    itemSeleccionado: String
) {
    NavigationBar(
        containerColor = RosaFondoNav,
        tonalElevation = 4.dp
    ) {
        // home
        NavigationBarItem(
            selected = itemSeleccionado == "Home",
            onClick = { viewModel.navigateTo(Screen.MainScreen)  },
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
        // perfil
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
        // favoritos
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

//pantalla principal de carrito
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carrito(viewModel: MainViewModel) {
    // Variable de estado para el Bottom Bar
    var itemNavSeleccionado by remember { mutableStateOf("Cart") } // Asumimos un estado 'Cart'

    Scaffold(
        topBar = { CartTopBar(viewModel = viewModel) },
        bottomBar = {
            // COMIENZA BOTTOM BAR
            Column {
                CartCheckoutBar(total = "$46.870")
                BottomNavBarPrincipal(
                    viewModel = viewModel,
                    itemSeleccionado = itemNavSeleccionado
                )
            }
        }
    ) { innerPadding ->

        // Columna perezosa (LazyColumn) para la lista de productos
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(dummyCartList) { product ->
                CartItem(product = product)
            }
        }
    }
}


// --- Componentes Individuales ---
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
            Text(product.name, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), fontSize = 15.sp, color = Cordovan)
            Spacer(modifier = Modifier.height(4.dp))
            Text(product.description, style = MaterialTheme.typography.bodySmall, color = Color.Gray, fontSize = 13.sp)
        }

        // Columna 3: Eliminar y Precio
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.weight(0.25f).height(80.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // Botón Eliminar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { /* Lógica Eliminar */ }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete ,
                    contentDescription = "Eliminar",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Eliminar", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }

            // Precio
            Text(
                product.price,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
                color = Cordovan
            )
        }
    }
    Divider(color = Color(0xFFF0F0F0), thickness = 1.dp, modifier = Modifier.padding(top = 16.dp))
}

@Composable
fun QuantitySelectorCart() {
    Row(
        modifier = Modifier
            .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("1", style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = Cordovan)
        Icon(Icons.Default.ArrowDropDown, contentDescription = "Cambiar cantidad", modifier = Modifier.size(20.dp), tint = Cordovan)
    }
}

// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun CarritoScreenPreview() {
    RosaPastelAppTheme {
        Carrito(viewModel = MainViewModel())
    }
}
