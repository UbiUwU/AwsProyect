package com.example.proyectoofertastrabajo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue,
    secondary = MediumBlue,
    tertiary = Teal,
    background = Color.Black,
    surface = DarkBlue,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = LightBlue,
    onSurface = LightBlue
)

private val LightColorScheme = lightColorScheme(
    primary = DarkBlue,
    secondary = MediumBlue,
    tertiary = Teal,
    background = Color.White,
    surface = LightBlue,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = DarkBlue,
    onSurface = DarkBlue
)

@Composable
fun ProyectoOfertasTrabajoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
