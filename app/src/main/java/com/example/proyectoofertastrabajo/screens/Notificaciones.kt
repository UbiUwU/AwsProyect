package com.example.proyectoofertastrabajo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun NotificacionesScreen(navController: NavHostController) {
    var areas by remember { mutableStateOf(listOf("Tecnología", "Salud", "Educación")) }
    var selectedArea by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Selecciona tus áreas de interés", style = MaterialTheme.typography.headlineSmall)
        areas.forEach { area ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedArea == area,
                    onClick = { selectedArea = area }
                )
                Text(area)
            }
        }
        Button(
            onClick = {
                // Guardar el área seleccionada para las notificaciones
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}
