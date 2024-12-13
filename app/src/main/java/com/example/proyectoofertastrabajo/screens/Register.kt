package com.example.proyectoofertastrabajo.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoofertastrabajo.R
import com.google.firebase.auth.FirebaseAuth
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.filled.ArrowDropDown
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    var nombres by remember { mutableStateOf(TextFieldValue("")) }
    var apellido1 by remember { mutableStateOf(TextFieldValue("")) }
    var apellido2 by remember { mutableStateOf(TextFieldValue("")) }
    var telefono by remember { mutableStateOf(TextFieldValue("")) }
    var direccion by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var selectedArea by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val areas = listOf("Área 1", "Área 2", "Área 3", "Área 4")  // Lista de áreas
    val mAuth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
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
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de registro",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(text = "Bienvenido", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
        }
        item {
            OutlinedTextField(
                value = nombres,
                onValueChange = { nombres = it },
                label = { Text(text = "Nombres") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = apellido1,
                onValueChange = { apellido1 = it },
                label = { Text(text = "Primer Apellido") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = apellido2,
                onValueChange = { apellido2 = it },
                label = { Text(text = "Segundo Apellido") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text(text = "Teléfono") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text(text = "Dirección") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    Log.d("DropdownMenu", "Expanded state: $expanded")
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    value = selectedArea ?: "Seleccione un área",
                    onValueChange = { /* No se usa para seleccionar el área */ },
                    label = { Text("Selecciona el Área") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    areas.forEach { area ->
                        DropdownMenuItem(
                            text = { Text(area) },
                            onClick = {
                                Log.d("DropdownMenu", "Selected area: $area")
                                selectedArea = area
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Código de validación y creación de usuario
        item {
            Button(
                onClick = {
                    isLoading = true
                    val emailText = email.text.trim()
                    val passwordText = password.text

                    // Validaciones
                    if (nombres.text.isEmpty() || apellido1.text.isEmpty() || apellido2.text.isEmpty() ||
                        telefono.text.isEmpty() || direccion.text.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || selectedArea == null
                    ) {
                        Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                        isLoading = false
                        return@Button
                    }

                    if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                        Toast.makeText(context, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
                        isLoading = false
                        return@Button
                    }

                    // Crear usuario en Firebase
                    mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener { task ->
                            isLoading = false
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                                navController.navigate("login") // Navegación a la pantalla de inicio de sesión
                            } else {
                                Log.w("RegisterScreen", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp))
                } else {
                    Text("Registrar")
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Iniciar sesión con:")
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fb),
                    contentDescription = "Iniciar sesión con Facebook",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { /* Lógica para Facebook */ }
                )
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Iniciar sesión con Google",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { }
                )
            }
        }
        item {
            TextButton(onClick = { navController.navigate("login") }) {
                Text("¿Ya tienes una cuenta? Inicia sesión aquí")
            }
        }
    }
}