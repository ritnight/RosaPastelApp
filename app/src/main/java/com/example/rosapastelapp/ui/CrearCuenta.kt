package com.example.rosapastelapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rosapastelapp.R
import com.example.rosapastelapp.ui.crearcuenta.model.CrearCuentaUiState
import com.example.rosapastelapp.ui.crearcuenta.viewmodel.CrearCuentaViewModel
import com.example.rosapastelapp.ui.theme.GrisClaroCampo
import com.example.rosapastelapp.ui.theme.RosaPastel

@Composable
fun CrearCuentaScreen(
    viewModel: CrearCuentaViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    CrearCuentaBody(
        uiState = uiState,
        onNombreChange = viewModel::onNombreChange,
        onCorreoChange = viewModel::onCorreoChange,
        onDireccionChange = viewModel::onDireccionChange,
        onContrasenaChange = viewModel::onContrasenaChange,
        onRepetirContrasenaChange = viewModel::onRepetirContrasenaChange,
        onIngresarClick = viewModel::onIngresarClick
    )
}

@Composable
fun CrearCuentaBody(
    uiState: CrearCuentaUiState,
    onNombreChange: (String) -> Unit,
    onCorreoChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onContrasenaChange: (String) -> Unit,
    onRepetirContrasenaChange: (String) -> Unit,
    onIngresarClick: () -> Unit
) {

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
            painter = painterResource(id = R.drawable.logo_rosa_pastel), // * pendiente el añadir el logo
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

        CampoFormulario(
            valor = uiState.nombre,
            onValorChange = onNombreChange,
            label = "Nombre",
            placeholder = "Ingresa tu nombre",
            error = uiState.errores.nombre
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoFormulario(
            valor = uiState.correo,
            onValorChange = onCorreoChange,
            label = "Correo electrónico",
            placeholder = "Ingresa tu correo electrónico",
            keyboardType = KeyboardType.Email,
            error = uiState.errores.correo
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoFormulario(
            valor = uiState.direccion,
            onValorChange = onDireccionChange,
            label = "Dirección",
            placeholder = "Ingresa tu dirección",
            error = uiState.errores.direccion
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoFormulario(
            valor = uiState.contrasena,
            onValorChange = onContrasenaChange,
            label = "Contraseña",
            placeholder = "Ingresa tu contraseña",
            keyboardType = KeyboardType.Password,
            esContrasena = true,
            error = uiState.errores.contrasena
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoFormulario(
            valor = uiState.repetirContrasena,
            onValorChange = onRepetirContrasenaChange,
            label = "Repetir contraseña",
            placeholder = "Repite tu contraseña",
            keyboardType = KeyboardType.Password,
            esContrasena = true,
            error = uiState.errores.repetirContrasena
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de "Ingresar"
        Button(
            onClick = onIngresarClick,
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

        // Footer
        Text(
            text = "© 2025 Rosa Pastel. Todos los derechos reservados.",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 24.dp)
        )
    }
}

@Composable
private fun CampoFormulario(
    valor: String,
    onValorChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    esContrasena: Boolean = false,
    error: String? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        TextField(
            value = valor,
            onValueChange = onValorChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, style = MaterialTheme.typography.bodyMedium) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (esContrasena) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            singleLine = true,
            isError = error != null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrisClaroCampo,
                unfocusedContainerColor = GrisClaroCampo,
                disabledContainerColor = GrisClaroCampo,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )

        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}
