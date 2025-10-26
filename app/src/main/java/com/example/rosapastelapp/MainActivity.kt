package com.example.rosapastelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rosapastelapp.ui.HomeScreen // Importa tu pantalla personalizada
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RosaPastelAppTheme {
                // Aquí se llama a la función Composable de tu pantalla principal.
                HomeScreen()
            }

        }
    }
}
