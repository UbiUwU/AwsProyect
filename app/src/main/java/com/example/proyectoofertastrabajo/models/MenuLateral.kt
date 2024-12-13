package com.example.proyectoofertastrabajo.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.proyectoofertastrabajo.navigation.NavScreen

sealed class MenuLateral(
    val icon: ImageVector,
    val title: String,
    val route: String
) {
    object Home : MenuLateral(Icons.Outlined.Home, "Convocatorias", NavScreen.ViewConvocatorias.name)
    object LoginScreen : MenuLateral(Icons.Outlined.Person, "Iniciar sesión", NavScreen.LoginScreen.name)
    object Registro : MenuLateral(Icons.Outlined.AccountCircle, "Registrarse", NavScreen.Registro.name)
    object Settings : MenuLateral(Icons.Outlined.Settings, "Configuración", NavScreen.SettingsScreen.name)
    object Help : MenuLateral(Icons.Outlined.Info, "Ayuda", NavScreen.HelpScreen.name)
}
