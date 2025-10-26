package ui

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rosapastelapp.R
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme

val RosaPastel = Color(0xFFE5A6B6)
val GrisClaroCampo = Color(0xFFF0F0F0)

@Composable
fun CrearCuenta() {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var repetirContrasena by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Rosa Pastel Cosmetics",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = "Crear Cuenta",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Formulario
        // nombre
        Text(
            text = "Nombre",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            textAlign = TextAlign.Start
        )
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ingresa tu nombre", style = MaterialTheme.typography.bodyMedium) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrisClaroCampo,
                unfocusedContainerColor = GrisClaroCampo,
                disabledContainerColor = GrisClaroCampo,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // correo
        Text(
            text = "Correo electrónico",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            textAlign = TextAlign.Start
        )
        TextField(
            value = correo,
            onValueChange = { correo = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ingresa tu correo electrónico", style = MaterialTheme.typography.bodyMedium) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrisClaroCampo,
                unfocusedContainerColor = GrisClaroCampo,
                disabledContainerColor = GrisClaroCampo,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // direccion
        Text(
            text = "Dirección",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            textAlign = TextAlign.Start
        )
        TextField(
            value = direccion,
            onValueChange = { direccion = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ingresa tu dirección", style = MaterialTheme.typography.bodyMedium) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrisClaroCampo,
                unfocusedContainerColor = GrisClaroCampo,
                disabledContainerColor = GrisClaroCampo,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // password
        Text(
            text = "Contraseña",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            textAlign = TextAlign.Start
        )
        TextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ingresa tu contraseña", style = MaterialTheme.typography.bodyMedium) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrisClaroCampo,
                unfocusedContainerColor = GrisClaroCampo,
                disabledContainerColor = GrisClaroCampo,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // repetir password
        Text(
            text = "Repetir contraseña",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
            textAlign = TextAlign.Start
        )
        TextField(
            value = repetirContrasena,
            onValueChange = { repetirContrasena = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Repite tu contraseña", style = MaterialTheme.typography.bodyMedium) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrisClaroCampo,
                unfocusedContainerColor = GrisClaroCampo,
                disabledContainerColor = GrisClaroCampo,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Boton de "Ingresar"
        Button(
            onClick = {
                // * logica de registro, pendiente
                println("Nombre: $nombre, Correo: $correo, Contraseña: $contrasena")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = RosaPastel
            )
        ) {
            Text(
                text = "Ingresar",
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // footer
        Text(
            text = "© 2025 Rosa Pastel. Todos los derechos reservados.",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 24.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CrearCuentaPreview() {
    RosaPastelAppTheme {
        CrearCuenta()
    }
}