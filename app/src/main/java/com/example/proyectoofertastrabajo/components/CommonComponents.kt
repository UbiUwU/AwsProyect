package com.example.proyectoofertastrabajo.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun NotificationIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "Notificaciones")
    }
}
