package com.example.loginconvocatoria

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoofertastrabajo.components.TopBar
import com.example.proyectoofertastrabajo.navigation.BancoNav
import com.example.proyectoofertastrabajo.components.Menu_Lateral
import com.example.proyectoofertastrabajo.ui.theme.ProyectoOfertasTrabajoTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoOfertasTrabajoTheme {
                PantallaPrincipal()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
                Log.d(TAG, "onStart Called")
            }

            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                Log.d(TAG, "onResume Called")
            }

            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
                Log.d(TAG, "onPause Called")
            }

            override fun onStop(owner: LifecycleOwner) {
                super.onStop(owner)
                Log.d(TAG, "onStop Called")
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                Log.d(TAG, "onDestroy Called")
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

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
    scope: kotlinx.coroutines.CoroutineScope
) {
    Scaffold(
        topBar = {
            TopBar(drawerState, navController)
        }
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

@Preview
@Composable
fun MyDessertClickerAppPreview() {
    ProyectoOfertasTrabajoTheme {
        PantallaPrincipal()
    }
}
