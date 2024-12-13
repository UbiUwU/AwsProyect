package com.example.proyectoofertastrabajo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.unit.dp
import com.example.proyectoofertastrabajo.screens.Convocatorias

@Composable
fun DropdownMenuFilter(convocatorias: List<Convocatorias>, onCitySelected: (String) -> Unit) {
    var expanded = remember { mutableStateOf(false) }
    var selectedCity = remember { mutableStateOf("Seleccionar una ciudad") }
    val cities = convocatorias.mapNotNull { it.ubicacion }.distinct().sorted()

    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopStart)
    ) {
        Button(onClick = { expanded.value = true }) {
            Row {
                Icon(
                    imageVector = Icons.Filled.LocationOn, // Ícono de ubicación
                    contentDescription = "Seleccionar ciudad"
                )
                Text(
                    text = selectedCity.value,
                    modifier = Modifier.padding(start = 8.dp) // Espacio entre el ícono y el texto
                )
            }
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            cities.forEach { city ->
                DropdownMenuItem(
                    text = { Text(text = city) },
                    onClick = {
                        selectedCity.value = city
                        onCitySelected(city)
                        expanded.value = false
                    }
                )
            }
        }
    }
}
