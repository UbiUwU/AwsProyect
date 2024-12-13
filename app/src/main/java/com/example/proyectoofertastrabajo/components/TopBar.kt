package com.example.proyectoofertastrabajo.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.proyectoofertastrabajo.models.MenuLateral
import com.example.proyectoofertastrabajo.navigation.currentRoute
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    drawerState: DrawerState,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val currentRoute = currentRoute(navController)
    val title = when (currentRoute) {
        MenuLateral.Home.route -> MenuLateral.Home.title
        MenuLateral.LoginScreen.route -> MenuLateral.LoginScreen.title
        MenuLateral.Registro.route -> MenuLateral.Registro.title
        else -> "Convocatorias"
    }
    CenterAlignedTopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.titleLarge, color = Color.White) },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Icon(Icons.Outlined.Menu, contentDescription = "Abrir menú lateral", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}
