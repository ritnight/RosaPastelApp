package com.example.rosapastelapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rosapastelapp.ui.theme.NewYorkPink
import com.example.rosapastelapp.viewmodel.MainViewModel

data class Address(
    val line1: String,
    val line2: String
)

// direcciones de prueba
val dummyAddressList = listOf(
    Address("Avenida SiempreViva, 742", "SPRINGFIELD, REGION METROPOLITANA"),
    Address("Avenida Antonio Varas 666", "Providencia, REGIÓN METROPOLITANA")
)

@Composable
fun Sucursales(viewModel: MainViewModel = MainViewModel()) {
    // Estado para guardar el índice de la dirección seleccionada
    var selectedAddressIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {

        // encabezado
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Ubicación",
                tint = NewYorkPink, // Usando tu color rosa
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "¿Donde quieres recibir tu compra?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // descripcion
        Text(
            text = "Ingresa tu ubicación y te mostraremos los productos disponibles con tiempos y costos de entrega precisos.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // titulo de seccion
        Text(
            text = "En mi dirección",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // lista de direcciones
        dummyAddressList.forEachIndexed { index, address ->
            AddressCard(
                address = address,
                isSelected = index == selectedAddressIndex,
                onClick = { selectedAddressIndex = index }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // enlace editar direcciones
        Text(
            text = "Editar direcciones",
            color = NewYorkPink,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier.clickable { /* Lógica para ir a editar direcciones */ }
        )


        Spacer(modifier = Modifier.weight(1f))

        // boton Guardar (al fondo)
        Button(
            onClick = { /* Lógica de guardar dirección seleccionada */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = NewYorkPink
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                "Guardar",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Un Composable reutilizable para mostrar una tarjeta de dirección seleccionable.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressCard(
    address: Address,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Usamos OutlinedCard para tener el borde fácilmente
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        // El color del borde cambia si está seleccionado
        border = BorderStroke(
            width = 2.dp,
            color = if (isSelected) NewYorkPink else Color.LightGray // Borde rosa si está activo
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = address.line1,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
            Text(
                text = address.line2,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SucursalesPreview() {
    Sucursales(viewModel = MainViewModel())
}