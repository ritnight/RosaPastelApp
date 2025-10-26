package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rosapastelapp.R
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme

// --- DEFINE TUS COLORES ---
val RosaPastel = Color(0xFFE5A6B6)
val RosaFondoNav = Color(0xFFFBEFF2)
val GrisClaroIcono = Color(0xFFEBEBEB)

@Composable
fun PerfilUsuario() {

    var itemNavSeleccionado by remember { mutableStateOf("Profile") }

    Scaffold(
        topBar = { TopBarPerfil() },
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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // --- 1. Información de Usuario ---
            Image(
                painter = painterResource(id = R.drawable.profile_pic_lara),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Lara Jean",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "larajean@gmail.com",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- 2. Botón Editar Perfil ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .clickable { /* Acción de editar perfil */ }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    modifier = Modifier
                        .size(40.dp)
                        .background(RosaPastel, CircleShape)
                        .padding(8.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Editar perfil",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- 3. Mensaje de Bienvenida ---
            Text(
                text = "¡Bienvenid@, Lara Jean!",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- 4. Sección de Configuraciones ---
            Text(
                text = "Configuraciones",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(16.dp))

            SettingsItem(icon = Icons.Default.Settings, text = "General")
            SettingsItem(icon = Icons.Default.Language, text = "Idioma")
            SettingsItem(icon = Icons.Default.HelpOutline, text = "Sobre nosotros")
            SettingsItem(icon = Icons.Default.Info, text = "Términos y Condiciones")
            SettingsItem(icon = Icons.Default.Lock, text = "Política de Privacidad")

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// --- BARRA SUPERIOR (TOP BAR) ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarPerfil() {
    CenterAlignedTopAppBar(
        title = {
            Text("Perfil", fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            IconButton(onClick = { /* Acción de volver */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Volver"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

// --- ITEM REUTILIZABLE PARA LA LISTA DE SETTINGS ---

@Composable
private fun SettingsItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier
                .size(40.dp)
                .background(GrisClaroIcono, CircleShape)
                .padding(8.dp),
            tint = Color.DarkGray
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.Gray
        )
    }
}


// --- BARRA DE NAVEGACIÓN INFERIOR (Copiada de PantallaPrincipal.kt) ---

@Composable
private fun BottomNavBarPrincipal(
    itemSeleccionado: String,
    onItemSelected: (String) -> Unit
) {
    NavigationBar(
        containerColor = RosaFondoNav,
        tonalElevation = 4.dp
    ) {
        // --- Ítem Home ---
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
                selectedIconColor = RosaPastel,
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        // --- Ítem Perfil ---
        NavigationBarItem(
            selected = itemSeleccionado == "Profile",
            onClick = { onItemSelected("Profile") },
            icon = {
                Icon(
                    imageVector = Icons.Default.PersonOutline,
                    contentDescription = "Perfil",
                    modifier = Modifier.size(if (itemSeleccionado == "Profile") 36.dp else 28.dp)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = RosaPastel,
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        // --- Ítem Favoritos ---
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
                selectedIconColor = RosaPastel,
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}


// --- PREVIEW ---

@Preview(showBackground = true)
@Composable
fun PerfilUsuarioPreview() {
    RosaPastelAppTheme {
        PerfilUsuario()
    }
}