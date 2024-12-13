package com.example.proyectoofertastrabajo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectoofertastrabajo.R
import com.example.proyectoofertastrabajo.navigation.NavScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val pages = listOf(
        OnboardingPage(
            imageRes = R.drawable.logo,
            title = "Bienvenido",
            description = "Descubre las mejores ofertas de trabajo desde una sola aplicación."
        ),
        OnboardingPage(
            imageRes = R.drawable.logo,
            title = "Navegación Fácil",
            description = "Encuentra rápidamente las convocatorias que mejor se adapten a tus habilidades."
        ),
        OnboardingPage(
            imageRes = R.drawable.logo,
            title = "Aplicación Rápida",
            description = "Envía tu CV y postúlate a las ofertas de forma sencilla y rápida."
        )
    )

    val currentPage = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = pages[currentPage.value].imageRes),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = pages[currentPage.value].title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = pages[currentPage.value].description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (currentPage.value > 0) {
                Button(onClick = { currentPage.value -= 1 }) {
                    Text("Atrás")
                }
            }

            if (currentPage.value < pages.size - 1) {
                Button(onClick = { currentPage.value += 1 }) {
                    Text("Siguiente")
                }
            } else {
                Button(onClick = { navController.navigate(NavScreen.StartScreen.name) }) {
                    Text("Empezar")
                }
            }
        }
    }
}

data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)
