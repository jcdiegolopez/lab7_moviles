package com.plataformas.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plataformas.lab7.navigation.AppNavigation
import com.plataformas.lab7.ui.components.TopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainApp(navController)
        }
    }
}

@Composable
fun MainApp(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Obtiene la ruta actual
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            val showBackButton = currentRoute != "category_screen"
            TopBar(navController = navController, title = "Food App", showBackButton = showBackButton)
        }
    ) { innerPadding ->
        AppNavigation(navController = navController, innerPadding = innerPadding )
    }
}
