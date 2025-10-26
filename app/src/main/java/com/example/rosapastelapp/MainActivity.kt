package com.example.rosapastelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rosapastelapp.ui.AddressSelectionScreen
import com.example.rosapastelapp.ui.CartScreen
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme
import com.example.rosapastelapp.ui.PerfilEditar
import ui.PantallaPrincipal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RosaPastelAppTheme {
                AddressSelectionScreen()
            }
        }
    }
}
