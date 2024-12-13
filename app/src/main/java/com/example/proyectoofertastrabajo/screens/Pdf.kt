package com.example.proyectoofertastrabajo.screens


import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadPdfScreen(navController: NavHostController, drawerState: DrawerState, scope: CoroutineScope) {    val context = LocalContext.current
    val pdfFileUri = remember { mutableStateOf<String?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        pdfFileUri.value = uri?.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { launcher.launch("application/pdf") }) {
            Text("Seleccionar PDF")
        }

        Spacer(modifier = Modifier.height(16.dp))

        pdfFileUri.value?.let {
            Text(text = "Archivo seleccionado: $it")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            Toast.makeText(context, "Archivo PDF subido con éxito", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }) {
            Text("Subir PDF")
        }
    }
}
