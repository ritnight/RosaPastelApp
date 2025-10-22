package com.example.rosapastelapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rosapastelapp.R
import com.example.rosapastelapp.ui.theme.Cordovan
import com.example.rosapastelapp.ui.theme.Marvelous
import com.example.rosapastelapp.ui.theme.NewYorkPink
import kotlin.OptIn // <-- Importación necesaria para @OptIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Rosa Pastel App") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween, // Mejor para distribuir los elementos verticalmente
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // botón para omitir inicio de sesión/ registro
            Button(
                onClick = { /* acción futura */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Cordovan
                ),
                border = BorderStroke(2.dp, NewYorkPink)
            ) {
                Text("Omitir")
            }


            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp), // Espacio entre los botones
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Fit
                )

                // boton para registrarse
                Button(
                    onClick = { /* acción futura */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Marvelous,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, NewYorkPink)
                ) {
                    Text("Registrarme")
                }

                // Botón para iniciar sesión
                Button(
                    onClick = { /* acción futura */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Cordovan
                    ),
                    border = BorderStroke(2.dp, NewYorkPink)
                ) {
                    Text("Iniciar Sesión")
                }
            }

            // texto de copyright
            Text(
                text = "© 2025 Rosa Pastel. Todos los derechos reservados.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
