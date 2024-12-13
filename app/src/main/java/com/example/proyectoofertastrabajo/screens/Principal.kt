package com.example.proyectoofertastrabajo.screens

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoofertastrabajo.components.Menu_Lateral
import com.example.proyectoofertastrabajo.components.TopBar
import com.example.proyectoofertastrabajo.navigation.BancoNav
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaPrincipal()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PantallaPrincipal() {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        Menu_Lateral(
            navController = navController,
            drawerState = drawerState
        ) {
            Contenido(
                navController = navController,
                drawerState = drawerState,
                scope = scope
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Contenido(
        navController: NavHostController,
        drawerState: DrawerState,
        scope: CoroutineScope
    ) {
        Scaffold(
            topBar = {
                TopBar(drawerState, navController)
            },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                BancoNav(navController = navController, drawerState = drawerState, scope = scope)
            }
        }
    }
}
