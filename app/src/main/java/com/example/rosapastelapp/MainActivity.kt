package com.example.rosapastelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rosapastelapp.navigation.NavigationEvent
import com.example.rosapastelapp.navigation.Screen
import com.example.rosapastelapp.viewmodel.MainViewModel
import com.example.rosapastelapp.ui.HomeScreen
import com.example.rosapastelapp.ui.InicioSesion
import com.example.rosapastelapp.ui.CrearCuenta
import com.example.rosapastelapp.ui.PantallaPrincipal
import com.example.rosapastelapp.ui.Sucursales
import com.example.rosapastelapp.ui.PerfilUsuario
import com.example.rosapastelapp.ui.Carrito
import com.example.rosapastelapp.ui.theme.RosaPastelAppTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RosaPastelAppTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun AppNavHost(viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        viewModel.navigationEvents.collectLatest { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> {
                    navController.navigate(route = event.route.route) {
                        event.popupToRoute?.let {
                            popUpTo(route = it.route) {
                                inclusive = event.inclusive
                            }
                        }
                        launchSingleTop = event.singleTop
                        restoreState = true
                    }
                }
                is NavigationEvent.PopBackStack -> navController.popBackStack()
                is NavigationEvent.NavigateUp -> navController.navigateUp()
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route, // Pantalla inicial
            modifier = Modifier.padding(innerPadding)
        ) {
            // rutas de navegación
            composable(route = Screen.Home.route) {
                HomeScreen(viewModel = viewModel)
            }
            composable(route = Screen.Login.route) {
                InicioSesion(viewModel = viewModel)
            }
            composable(route = Screen.Register.route) {
                CrearCuenta(viewModel = viewModel)
            }
            composable(route = Screen.MainScreen.route) {
                PantallaPrincipal(viewModel = viewModel)
            }
            composable(route = Screen.Stores.route) {
                Sucursales(viewModel = viewModel)
            }
            composable(route = Screen.Profile.route) {
                PerfilUsuario(viewModel = viewModel)
            }
            composable(route = Screen.Cart.route) {
                Carrito(viewModel = viewModel)
            }
        }
    }
}
