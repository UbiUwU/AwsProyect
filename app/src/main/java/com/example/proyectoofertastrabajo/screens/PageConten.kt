package com.example.proyectoofertastrabajo.screens

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageContent(
    navController: NavController,
    companyName: String,
    salary: String,
    educationRequired: String,
    phone: String,
    convocatoriaName: String,
    jobDescription: String,
    city: String,
    adminUnit: String,
    contractType: String,
    publicationDate: String,
    endDate: String
) {
    val decodedCompanyName = URLDecoder.decode(companyName, StandardCharsets.UTF_8.toString())
    val decodedSalary = URLDecoder.decode(salary, StandardCharsets.UTF_8.toString())
    val decodedEducationRequired = URLDecoder.decode(educationRequired, StandardCharsets.UTF_8.toString())
    val decodedPhone = URLDecoder.decode(phone, StandardCharsets.UTF_8.toString())
    val decodedConvocatoriaName = URLDecoder.decode(convocatoriaName, StandardCharsets.UTF_8.toString())
    val decodedJobDescription = URLDecoder.decode(jobDescription, StandardCharsets.UTF_8.toString())
    val decodedCity = URLDecoder.decode(city, StandardCharsets.UTF_8.toString())
    val decodedAdminUnit = URLDecoder.decode(adminUnit, StandardCharsets.UTF_8.toString())
    val decodedContractType = URLDecoder.decode(contractType, StandardCharsets.UTF_8.toString())
    val decodedPublicationDate = URLDecoder.decode(publicationDate, StandardCharsets.UTF_8.toString())
    val decodedEndDate = URLDecoder.decode(endDate, StandardCharsets.UTF_8.toString())

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val pdfFileUri = remember { mutableStateOf<String?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        pdfFileUri.value = uri?.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Botón para regresar en la parte superior izquierda
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Regresar",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        ProjectCard(
            title = decodedCompanyName,
            subject = "3",
            description = "$decodedSalary\nRequisito Educativo: $decodedEducationRequired",
            pdfFileUri = pdfFileUri.value,
            onSelectPdf = { launcher.launch("application/pdf") },
            onUploadPdf = {
                Toast.makeText(context, "Archivo PDF subido con éxito", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            },
            onApply = { /* Aquí puedes agregar la lógica para la postulación */ },
            telefonoDependencia = "9876543211",
            nombreConvocatoria = decodedConvocatoriaName,
            descripcionPuesto = decodedJobDescription,
            ciudad = decodedCity,
            unidadAdminVacante = decodedAdminUnit,
            tipoContrato = decodedContractType,
            fechaPublicacion = decodedPublicationDate,
            fechaFinalizacion = decodedEndDate
        )
    }
}

@Composable
fun ProjectCard(
    title: String,
    subject: String,
    description: String,
    pdfFileUri: String?,
    onSelectPdf: () -> Unit,
    onUploadPdf: () -> Unit,
    onApply: () -> Unit,
    telefonoDependencia: String,
    nombreConvocatoria: String,
    descripcionPuesto: String,
    ciudad: String,
    unidadAdminVacante: String,
    tipoContrato: String,
    fechaPublicacion: String,
    fechaFinalizacion: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Título
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF184059),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))



            // Salario
            InfoSection(header = "Salario:", value = description.split("\n")[0])

            // Requisito Educativo
            InfoSection(header = "Requisito Educativo:", value = description.split("\n")[1].replace("Requisito Educativo: ", ""))


            // Teléfono de la Dependencia
            InfoSection(header = "Teléfono Dependencia:", value = telefonoDependencia)

            // Nombre de la Convocatoria
            InfoSection(header = "Nombre Convocatoria:", value = nombreConvocatoria)

            // Descripción del Puesto
            InfoSection(header = "Descripción del Puesto:", value = descripcionPuesto)

            // Ciudad
            InfoSection(header = "Ciudad:", value = ciudad)

            // Unidad Administrativa Vacante
            InfoSection(header = "Unidad Administrativa Vacante:", value = unidadAdminVacante)

            // Tipo de Contrato
            InfoSection(header = "Tipo de Contrato:", value = tipoContrato)

            // Fechas
            InfoSection(header = "Fecha de Publicación:", value = fechaPublicacion)
            InfoSection(header = "Fecha de Finalización:", value = fechaFinalizacion)

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onSelectPdf) {
                Text("Enviar CV en PDF")
            }

            pdfFileUri?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Archivo seleccionado: $it")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onUploadPdf) {
                Text("Postularse")
            }
        }
    }
}

@Composable
fun InfoSection(header: String, value: String) {
    Column {
        Text(
            text = header,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFF5D8AA6), // Color para el encabezado
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black // Color para el valor
            )
        )
    }
}