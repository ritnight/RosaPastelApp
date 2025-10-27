package com.example.rosapastelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rosapastelapp.R
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme
import com.example.rosapastelapp.viewmodel.MainViewModel

val RosaPastelBanner = Color(0xFFE5A6B6)
val GrisClaroBanner = Color(0xFFF0F0F0)
val RosaFondoNav = Color(0xFFFBEFF2)

@Composable
fun PantallaPrincipal(viewModel: MainViewModel) {
    var tabSeleccionada by remember {mutableStateOf(0) }

    var itemNavSeleccionado by remember { mutableStateOf("Home") }

    Scaffold(
        topBar = { TopBarPrincipal() },
        bottomBar = {
            BottomNavBarPrincipal(
                itemSeleccionado = itemNavSeleccionado,
                onItemSelected = { itemNavSeleccionado = it }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            PromoBanner()

            TabsBelleza(
                tabSeleccionada = tabSeleccionada,
                onTabSelected = { tabSeleccionada = it }
            )

            if (tabSeleccionada == 0) {
                ContenidoBelleza()
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Contenido de Skincare")
                }
            }
        }
    }
}

// BARRA SUPERIOR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarPrincipal() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Dirección
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Ubicación",
                            modifier = Modifier.size(14.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Avenida Siempreviva 742",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        },
        // Iconos a la derecha
        actions = {
            IconButton(onClick = { /* Acción de búsqueda */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    modifier = Modifier.size(28.dp)
                )
            }
            IconButton(onClick = { /* Acción de carrito */ }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrito",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    )
}

// BANNER DE PROMOCIÓN

@Composable
private fun PromoBanner() {
    Surface(
        color = RosaPastelBanner,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Con compras arriba de $30.000 CLP, tienes envío incluido!",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

// BELLEZA / SKINCARE

@Composable
private fun TabsBelleza(tabSeleccionada: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("BELLEZA", "SKINCARE")

    TabRow(
        selectedTabIndex = tabSeleccionada,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.Gray
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = tabSeleccionada == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        style = if (tabSeleccionada == index) {
                            MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        } else {
                            MaterialTheme.typography.titleSmall
                        },
                        // Color negro si está seleccionado, gris si no
                        color = if (tabSeleccionada == index) Color.Black else Color.Gray
                    )
                }
            )
        }
    }
}

// PESTAÑA \"BELLEZA\" 
@Composable
private fun ContenidoBelleza() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.descuentos),
            contentDescription = "Banner Descuentos",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Descuentos
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = GrisClaroBanner),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "DESCUENTOS NYX",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp // Espaciado de letras
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "¡Aprovecha los descuentos de hasta 50% por mitad de temporada!",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Productos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductoItem(
                imagenId = R.drawable.kiko_milano_gloss,
                marca = "KIKO MILANO",
                nombre = "3D Hydra Gloss",
                precio = "$15.990"
            )
            ProductoItem(
                imagenId = R.drawable.nyx_epic_ink,
                marca = "Nyx Cosmetics",
                nombre = "Delineador de Ojos",
                precio = "$9.950"
            )
            ProductoItem(
                imagenId = R.drawable.tinta_essence,
                marca = "Maybelline",
                nombre = "Super Stay Teddy Tint",
                precio = "$10.990"
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ProductoItem(
    imagenId: Int,
    marca: String,
    nombre: String,
    precio: String
) {
    Column(
        modifier = Modifier.width(150.dp)
    ) {
        Image(
            painter = painterResource(id = imagenId),
            contentDescription = nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = marca,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )
        Text(
            text = nombre,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1 // Solo una línea
        )
        Text(
            text = precio,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun BottomNavBarPrincipal(
    itemSeleccionado: String,
    onItemSelected: (String) -> Unit
) {
    NavigationBar(
        containerColor = RosaFondoNav,
        tonalElevation = 4.dp
    ) {
        // Home
        NavigationBarItem(
            selected = itemSeleccionado == "Home",
            onClick = { onItemSelected("Home") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Inicio",
                    modifier = Modifier.size(if (itemSeleccionado == "Home") 36.dp else 28.dp)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = RosaPastelBanner,
                indicatorColor = Color.Transparent
            )
        )
        // Perfil
        NavigationBarItem(
            selected = itemSeleccionado == "Profile",
            onClick = { onItemSelected("Profile") },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person ,
                    contentDescription = "Perfil",
                    modifier = Modifier.size(if (itemSeleccionado == "Profile") 36.dp else 28.dp)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = RosaPastelBanner,
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        // Favoritos
        NavigationBarItem(
            selected = itemSeleccionado == "Favorites",
            onClick = { onItemSelected("Favorites") },
            icon = {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favoritos",
                    modifier = Modifier.size(if (itemSeleccionado == "Favorites") 36.dp else 28.dp)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = RosaPastelBanner,
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    RosaPastelAppTheme { 
        PantallaPrincipal()
    }
}
