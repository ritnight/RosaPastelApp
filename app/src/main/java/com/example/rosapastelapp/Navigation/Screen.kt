package com.example.rosapastelapp.navigation

sealed class Screen(val route: String) {

    // Corresponde a HomeScreen.kt
    data object Home : Screen(route = "home_page")

    // Corresponde a InicioSesion.kt
    data object Login : Screen(route = "login_page")

    // Corresponde a PerfilUsuario.kt
    data object Profile : Screen(route = "profile_page")

    // Corresponde a CartScreen.kt (Carrito)
    data object Cart : Screen(route = "cart_page")

    // Corresponde a CrearCuenta.kt
    data object Register : Screen(route = "register_page")

    // Corresponde a DetalleProducto.kt
    data object ProductDetail : Screen(route = "product_detail_page")

    // Corresponde a EditarUsuario.kt
    data object EditProfile : Screen(route = "edit_profile_page")

    // Corresponde a PantallaPrincipal.kt
    data object MainScreen : Screen(route = "main_screen_page")

    // Corresponde a Sucursales.kt
    data object Stores : Screen(route = "stores_page")

    //corresponde a Favoritos.kt
    data object Favorites : Screen(route = "favorites_page")
    //corresponde a Posts.kt
    data object Posts : Screen(route = "posts_page")
}



