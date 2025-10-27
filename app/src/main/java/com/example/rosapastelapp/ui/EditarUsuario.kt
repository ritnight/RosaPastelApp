package com.example.rosapastelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.rosapastelapp.R
import com.example.rosapastelapp.ui.theme.Cordovan
import com.example.rosapastelapp.ui.theme.NewYorkPink
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme
import com.example.rosapastelapp.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilEditar(viewModel: MainViewModel) {

    // valores de ejemplo para los campos editables
    val nombre = "Lara Jean"
    val correo = "larajean@gmail.com"
    val contrasena = "************"
    val direccion = "Calle Falsa 123"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar perfil", color = Cordovan) },
                navigationIcon = {
                    IconButton(onClick = { /* Acción para volver */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Cordovan)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(60.dp)
                    .border(1.dp, Color.LightGray)
                    .padding(horizontal = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Inicio",
                        modifier = Modifier.size(30.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_monito),
                        contentDescription = "Perfil",
                        modifier = Modifier.size(30.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_corazon),
                        contentDescription = "Favoritos",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))


            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, NewYorkPink, CircleShape)
                )
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Editar foto",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(Cordovan)
                        .padding(4.dp)
                        .size(20.dp)
                )
            }

            Text(
                text = nombre,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                fontWeight = FontWeight.Bold,
                color = Cordovan,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = correo,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // nombre Completo
            EditableProfileField(label = "Nombre completo", value = nombre, isPassword = false)
            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 24.dp))

            // correo electrónico
            EditableProfileField(label = "Correo electrónico", value = correo, isPassword = false)
            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 24.dp))

            // contraseña
            EditableProfileField(label = "Contraseña", value = "***********", isPassword = true)
            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 24.dp))

            // dirección
            EditableProfileField(label = "Dirección", value = direccion, isPassword = false)

            Spacer(modifier = Modifier.height(48.dp))

            Button(onClick = { /* acción futura */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(percent = 50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NewYorkPink,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Guardar Cambios",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}

@Composable
fun EditableProfileField(label: String, value: String, isPassword: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = Cordovan,
                fontWeight = FontWeight.SemiBold
            )
        }
        Icon(
            Icons.Default.Edit,
            contentDescription = "Editar $label",
            tint = Cordovan,
            modifier = Modifier.size(20.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PerfilEditarPreview() {
    RosaPastelAppTheme {
        PerfilEditar()
    }
}