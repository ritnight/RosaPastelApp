package com.example.rosapastelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
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
import com.example.rosapastelapp.data.model.Producto
import com.example.rosapastelapp.navigation.Screen
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme
import com.example.rosapastelapp.viewmodel.MainViewModel
import coil.compose.AsyncImage


val RosaPastelBanner = Color(0xFFE5A6B6)
val GrisClaroBanner = Color(0xFFF0F0F0)
val RosaFondoNav = Color(0xFFFBEFF2)

@Composable
fun PantallaPrincipal(viewModel: MainViewModel) {

    var tabSeleccionada by remember { mutableStateOf(0) }
    var itemNavSeleccionado by remember { mutableStateOf("Home") }

    // Productos que vienen del backend
    val productos by viewModel.productos.collectAsState()

    // Cargar productos una sola vez cuando se entra a esta pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarProductos()
    }

    // Filtrar por categoría según la pestaña
    val productosFiltrados = when (tabSeleccionada) {
        0 -> productos.filter { it.categoria.equals("MAQUILLAJE", ignoreCase = true) }
        else -> productos.filter { it.categoria.equals("SKINCARE", ignoreCase = true) }
    }

    Scaffold(
        topBar = { TopBarPrincipal(viewModel = viewModel) },
        bottomBar = {
            BottomNavBarPrincipal(
                viewModel = viewModel,
                itemSeleccionado = itemNavSeleccionado,
                onItemSeleccionado = { itemNavSeleccionado = it }
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

            ContenidoBelleza(
                viewModel = viewModel,
                productos = productosFiltrados,
                tabSeleccionada = tabSeleccionada
            )
        }
    }
}

// BARRA SUPERIOR
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarPrincipal(viewModel: MainViewModel) {
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
                Column(
                    modifier = Modifier.clickable {
                        viewModel.navigateTo(Screen.Stores)
                    }
                ) {
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
        actions = {
            IconButton(onClick = { /* Acción de búsqueda */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    modifier = Modifier.size(28.dp)
                )
            }
            IconButton(onClick = { viewModel.navigateTo(Screen.Cart) }) {
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
                        color = if (tabSeleccionada == index) Color.Black else Color.Gray
                    )
                }
            )
        }
    }
}

// CONTENIDO SEGÚN LA PESTAÑA (BELLEZA / SKINCARE)
@Composable
private fun ContenidoBelleza(
    viewModel: MainViewModel,
    productos: List<Producto>,
    tabSeleccionada: Int
) {
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
                    text = if (tabSeleccionada == 0) "DESCUENTOS MAQUILLAJE" else "DESCUENTOS SKINCARE",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
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

        // Título de la sección de productos
        Text(
            text = if (tabSeleccionada == 0) "Productos de Maquillaje" else "Productos de Skincare",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (productos.isEmpty()) {
            Text(
                text = "Cargando productos o no hay productos para esta categoría.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productos) { producto ->
                    ProductoBackendItem(
                        producto = producto,
                        viewModel = viewModel
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ProductoBackendItem(
    producto: Producto,
    viewModel: MainViewModel
) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable {viewModel.seleccionarProducto(producto)
                        viewModel.navigateTo(Screen.ProductDetail) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            // IMAGEN DEL PRODUCTO DESDE imagenUrl
            AsyncImage(
                model = producto.imagenUrl,
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // NOMBRE
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            // PRECIO
            Text(
                text = "Precio: $${producto.precio}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


// NAV BAR INFERIOR
@Composable
private fun BottomNavBarPrincipal(
    viewModel: MainViewModel,
    itemSeleccionado: String,
    onItemSeleccionado: (String) -> Unit
) {
    NavigationBar(
        containerColor = RosaFondoNav,
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            selected = itemSeleccionado == "Home",
            onClick = {
                onItemSeleccionado("Home")
                viewModel.navigateTo(Screen.MainScreen)
            },
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
        NavigationBarItem(
            selected = itemSeleccionado == "Profile",
            onClick = {
                onItemSeleccionado("Profile")
                viewModel.navigateTo(Screen.Profile)
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
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
        NavigationBarItem(
            selected = itemSeleccionado == "Favorites",
            onClick = {
                onItemSeleccionado("Favorites")
                viewModel.navigateTo(Screen.Favorites)
            },
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
        PantallaPrincipal(viewModel = MainViewModel())
    }
}
