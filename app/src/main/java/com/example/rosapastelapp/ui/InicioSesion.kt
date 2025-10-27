package com.example.rosapastelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rosapastelapp.R
import com.example.rosapastelapp.ui.theme.Cordovan
import com.example.rosapastelapp.ui.theme.FondoGrisClaro
import com.example.rosapastelapp.ui.theme.NewYorkPink
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme

@Composable
fun  InicioSesion() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(72.dp))

                /*logo de la app*/
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Inicio Sesión",
                    color = Cordovan,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Correo electrónico",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    color = Cordovan,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(
                            "Ingresa tu correo electrónico",
                            color = Color.Gray,
                            fontSize = 13.sp
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(percent = 50),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = FondoGrisClaro,
                        unfocusedContainerColor = FondoGrisClaro,
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = NewYorkPink,
                        cursorColor = NewYorkPink
                    ),

                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Contraseña",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    color = Cordovan,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Ingresa tu contraseña", color = Color.Gray, fontSize = 13.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = FondoGrisClaro,
                        unfocusedContainerColor = FondoGrisClaro,
                        unfocusedBorderColor = Color.LightGray,
                        focusedBorderColor = NewYorkPink,
                        cursorColor = NewYorkPink
                    ),
                    shape = RoundedCornerShape(percent = 50),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(32.dp))

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
                        "Ingresar",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(100.dp))
            }

            // Texto de derechos de autor
            Text(
                text = "© 2025 Rosa Pastel. Todos los derechos reservados.",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                color = Cordovan,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun InicioSesionPreview() {
    RosaPastelAppTheme {
        InicioSesion()
    }
}