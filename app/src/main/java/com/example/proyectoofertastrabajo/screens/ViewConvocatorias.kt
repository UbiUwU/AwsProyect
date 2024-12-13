package com.example.proyectoofertastrabajo.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectoofertastrabajo.R
import com.example.proyectoofertastrabajo.components.DropdownMenuFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.example.proyectoofertastrabajo.components.NotificationIconButton
import com.example.proyectoofertastrabajo.navigation.NavScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewConvocatorias(navController: NavHostController, drawerState: DrawerState, scope: CoroutineScope) {
    var searchText by remember { mutableStateOf("") }
    var convocatorias by remember { mutableStateOf<List<Convocatorias>>(emptyList()) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        fetchConvocatorias(
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI0IiwianRpIjoiZjk4MTRlNGQ1Y2FhNWZhZTVlMmQ3Y2ZmMWY5ZWVmZjJhZDIzMTNmMjlkZDY0NjYwNDZkMTA2Y2FhZGNiMzMxNDgwNjBmY2EwNTI4MDRlMDAiLCJpYXQiOjE3MzIyMjEyODAuOTM1ODQ3LCJuYmYiOjE3MzIyMjEyODAuOTM1ODU0LCJleHAiOjE3NjM3NTcyODAuNjUwOTE1LCJzdWIiOiI0MCIsInNjb3BlcyI6W119.YEDxL1Mh0h8XSol7ps4_CruCrqZkide9VPmvkauWvY1pgb8IV-25PvXXG8FRtYWlyCqQCBSam2IG9jfsVLMZwk2GLzb2_7o8nkyldRwg490UoEDuRJ5yoxR_SQngZsCriRqhsd10nPKcIr0HRCdKdTtSZyUGE7wTdpZl81GlLPBqmTjwol4SHnZFJT1brntNY6DzgNtZzR4M2DxVx0wlc6SpKGh1TRjVla-WMqqqVFmgoLdqccytgtMp85TXcUd7v6eLbIhvfYuZkL5gQe3BclrPQg-f1-L02gzTxEzgr04AOtDZdP9-EH2YswphhOd0eWxZeT9lSdL_-gy4tMjfkJpbXwIcWSJpM_whnOSPEP7H03bG10za_VhfJPoLYxUNemv4etuv6wFeH1SKiuR--rgU3tYpWTXPEoppyY6J03n0_HENRYdOXgOnj15B8txqH-Vq9VCjFeiVmG5eOlqsVPbKXo3z_2aMoMiimFaH0T-iJ5dF5GfGlbzj997k5uiyM6uQKosP08BsWqqwwVbHZHcy57mCozivFG-7G4GZL2543yHEgCwxfN4-s8MshQK5sVun_VuFpUrdEeyLNUkO7rW9K1PpABQ6TKKGTYeMzrJauK75RGVAoIzj87EW1o2kfITr2A9kRlQ4he4-gDKylE1ayC-zNf4gzMWok1xFXX8",
            onSuccess = { convocatoriasList ->
                Log.d("API_SUCCESS", "Convocatorias obtenidas: ${convocatoriasList.size}")
                convocatorias = convocatoriasList
            },
            onError = { error ->
                Log.e("API_ERROR", error)
                errorMessage = error
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DropdownMenuFilter(convocatorias, onCitySelected = { selectedCity ->
                searchText = selectedCity
            })

            NotificationIconButton {
                navController.navigate(NavScreen.Notification.name)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        } else {
            val filteredConvocatorias = convocatorias.filter {
                it.ubicacion?.contains(searchText, ignoreCase = true) == true
            }

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(filteredConvocatorias.size) { index ->
                    val convocatoria = filteredConvocatorias[index]
                    val imageRes = when (convocatoria.fecha_publicacion) {
                        "Dependencias Secretaría de Educación" -> R.drawable.i1
                        "Dependencias de Salud" -> R.drawable.logo
                        "Dependencias de Seguridad Pública" -> R.drawable.i1
                        "Dependencias de Desarrollo Urbano" -> R.drawable.logo
                        else -> R.drawable.logo // Imagen predeterminada
                    }

                    MenuCard(
                        companyName = convocatoria.empresa?.nombre ?: "N/A",
                        salary = convocatoria.salario ?: "N/A",
                        position = convocatoria.titulo ?: "N/A",
                        city = convocatoria.ubicacion ?: "N/A",
                        vacancies = convocatoria.id ?: 0,
                        imageRes = imageRes,
                        onClick = {
                            val encodedFields = listOf(
                                convocatoria.empresa?.nombre,
                                convocatoria.salario,
                                convocatoria.requisitos,
                                convocatoria.tipo_contrato,
                                convocatoria.titulo,
                                convocatoria.descripcion,
                                convocatoria.ubicacion,
                                convocatoria.nivel_requerido,
                                convocatoria.tipo_contrato,
                                convocatoria.fecha_publicacion,
                                convocatoria.fecha_actualizacion
                            ).map { URLEncoder.encode(it, StandardCharsets.UTF_8.toString()) }

                            navController.navigate("page_content/${encodedFields.joinToString("/")}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MenuCard(
    companyName: String,
    salary: String,
    position: String,
    city: String,
    vacancies: Int,
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
            .shadow(8.dp, RoundedCornerShape(16.dp)),  // Agregar sombra a la tarjeta
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface  // Usar fondo claro
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = imageRes), // Asigna la imagen correspondiente
                contentDescription = "Ícono de Empresa",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)  // Asegura que la columna ocupe el espacio disponible
            ) {
                Text(
                    text = companyName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = position,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
                Text(
                    text = "Sueldo: $salary",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = "Ciudad: $city",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = "Vacantes: $vacancies",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }
}

private fun fetchConvocatorias(authToken: String, onSuccess: (List<Convocatorias>) -> Unit, onError: (String) -> Unit) {
    val retrofit = RetrofitInstance.api  // Asegúrate de tener la instancia de Retrofit correctamente configurada.

    CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
        try {
            val response = retrofit.getConvocatorias(authToken)
            if (response.isSuccessful) {
                response.body()?.let { convocatorias ->
                    Log.d("API_RESPONSE", "Respuesta de la API: $convocatorias")
                    // Ahora estamos esperando directamente una lista de convocatorias
                    onSuccess(convocatorias) // Pasamos la lista directamente
                } ?: onError("Error: Lista vacía")
            } else {
                Log.e("API_ERROR", "Error en la respuesta: ${response.code()}")
                onError("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("API_ERROR", "Excepción: ${e.message}")
            onError(e.message ?: "Error desconocido")
        }
    }
}

