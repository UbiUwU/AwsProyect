package com.example.proyectoofertastrabajo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.material3.DrawerState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyectoofertastrabajo.screens.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun BancoNav(
    navController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.name
    ) {
        composable(NavScreen.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(NavScreen.StartScreen.name) {
            StartScreen(navController = navController)
        }
        composable(NavScreen.Notification.name) {
            NotificacionesScreen(navController = navController)
        }
        composable(NavScreen.ViewConvocatorias.name) {
            ViewConvocatorias(navController = navController, drawerState = drawerState, scope = scope)
        }
        composable(NavScreen.LoginScreen.name) {
            LoginScreen(navController = navController, drawerState = drawerState, scope = scope)
        }
        composable(NavScreen.Registro.name) {
            RegisterScreen(navController = navController, drawerState = drawerState, scope = scope)
        }
        composable(
            "page_content/{companyName}/{salary}/{educationRequired}/{phone}/{convocatoriaName}/{jobDescription}/{city}/{adminUnit}/{contractType}/{publicationDate}/{endDate}",
            arguments = listOf(
                navArgument("companyName") { type = NavType.StringType },
                navArgument("salary") { type = NavType.StringType },
                navArgument("educationRequired") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType },
                navArgument("convocatoriaName") { type = NavType.StringType },
                navArgument("jobDescription") { type = NavType.StringType },
                navArgument("city") { type = NavType.StringType },
                navArgument("adminUnit") { type = NavType.StringType },
                navArgument("contractType") { type = NavType.StringType },
                navArgument("publicationDate") { type = NavType.StringType },
                navArgument("endDate") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            PageContent(
                navController = navController,
                companyName = backStackEntry.arguments?.getString("companyName") ?: "",
                salary = backStackEntry.arguments?.getString("salary") ?: "",
                educationRequired = backStackEntry.arguments?.getString("educationRequired") ?: "",
                phone = backStackEntry.arguments?.getString("phone") ?: "",
                convocatoriaName = backStackEntry.arguments?.getString("convocatoriaName") ?: "",
                jobDescription = backStackEntry.arguments?.getString("jobDescription") ?: "",
                city = backStackEntry.arguments?.getString("city") ?: "",
                adminUnit = backStackEntry.arguments?.getString("adminUnit") ?: "",
                contractType = backStackEntry.arguments?.getString("contractType") ?: "",
                publicationDate = backStackEntry.arguments?.getString("publicationDate") ?: "",
                endDate = backStackEntry.arguments?.getString("endDate") ?: ""
            )
        }
        composable(NavScreen.UploadPdfScreen.name) {
            UploadPdfScreen(navController = navController, drawerState = drawerState, scope = scope)
        }
        composable(NavScreen.SettingsScreen.name) {
            SettingsScreen(navController = navController, drawerState = drawerState, scope = scope)
        }
        composable(NavScreen.HelpScreen.name) {
            HelpScreen(navController = navController, drawerState = drawerState, scope = scope)
        }
    }
}