package com.plataformas.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plataformas.lab7.navigation.AppNavigation
import com.plataformas.lab7.ui.components.TopBar
import com.plataformas.lab7.viewmodel.CategoryViewModel
import com.plataformas.lab7.viewmodel.MealViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var mealViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = (applicationContext as MyApp).categoryRepository
        mealViewModel = ViewModelProvider(
            this,
            MealViewModelFactory(repository)
        )[CategoryViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MainApp(navController = rememberNavController(),mealViewModel)
        }
    }
}

@Composable
fun MainApp(navController: NavHostController, mealViewModel: CategoryViewModel) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Obtiene la ruta actual
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            val showBackButton = currentRoute != "category_screen"
            TopBar(navController = navController, title = "Food App", showBackButton = showBackButton)
        }
    ) { innerPadding ->
        AppNavigation(navController = navController, mealViewModel = mealViewModel, innerPadding = innerPadding )
    }
}
