package com.example.rosapastelapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.example.rosapastelapp.navigation.Screen
import com.example.rosapastelapp.ui.theme.Cordovan
import com.example.rosapastelapp.ui.theme.Marvelous
import com.example.rosapastelapp.ui.theme.NewYorkPink
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme
import com.example.rosapastelapp.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) { //acepta el  viewmodel
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Rosa Pastel App", color = Cordovan) })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //bloque superior boton omitir
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                // botón para omitir inicio de sesión/ registro
                Button(
                    onClick = { viewModel.navigateTo(Screen.MainScreen) },  //accion del boton lleva a pantalla principal
                    modifier = Modifier.align(Alignment.CenterEnd),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Cordovan
                    ),
                    border = BorderStroke(1.dp, Cordovan)
                ) {
                    Text(
                        "Omitir",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }


            // bloque central logo y botones (inicio de sesion y registro)
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().weight(1f, fill = false)
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
                    onClick = {viewModel.navigateTo(Screen.Register)},  //accion del boton lleva a pantalla de registro
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NewYorkPink,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        "Registrarme",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // boton para iniciar sesión
                Button(
                    onClick = { viewModel.navigateTo(Screen.Login) }, // accion del boton lleva a pantalla de inicio de sesion
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Cordovan
                    ),
                    border = BorderStroke(2.dp, Cordovan)
                ) {
                    Text(
                        "Iniciar Sesión",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Button(onClick = { viewModel.navigateTo(Screen.Posts) }) {
                    Text("Probar API")
                }

            }

            // bloque inferior copyright
            Text(
                text = "© 2025 Rosa Pastel. Todos los derechos reservados.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp),
                color = Cordovan,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    RosaPastelAppTheme {
        HomeScreen(viewModel = MainViewModel())
    }
}