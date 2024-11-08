package com.plataformas.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.plataformas.lab7.navigation.AppNavigation
import com.plataformas.lab7.ui.components.TopBar
import com.plataformas.lab7.viewmodel.CategoryViewModel
import com.plataformas.lab7.viewmodel.MealViewModelFactory
import com.plataformas.lab7.viewmodel.SupermarketViewModel
import com.plataformas.lab7.viewmodel.SupermarketViewModelFactory
import android.Manifest
import android.content.pm.PackageManager

class MainActivity : ComponentActivity() {
    private val REQUEST_CODE_PERMISSIONS = 10
    private lateinit var mealViewModel: CategoryViewModel
    private lateinit var supermarketViewModel: SupermarketViewModel

    private fun allPermissionsGranted() = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ).all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_PERMISSIONS
            )
        }


        val repositorymeal = (application as MyApp).categoryRepository
        val respositorySupermarket = (application as MyApp).supermarketRepository
        mealViewModel = ViewModelProvider(
            this,
            MealViewModelFactory(repositorymeal)
        )[CategoryViewModel::class.java]

        supermarketViewModel = ViewModelProvider(
            this,
            SupermarketViewModelFactory(respositorySupermarket)
        )[SupermarketViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MainApp(navController = rememberNavController(),mealViewModel,supermarketViewModel)
        }
    }
}

@Composable
fun MainApp(navController: NavHostController, mealViewModel: CategoryViewModel, supermarketViewModel: SupermarketViewModel) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Obtiene la ruta actual
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            val showBackButton = currentRoute != "category_screen"
            TopBar(navController = navController, title = "Food App", showBackButton = showBackButton)
        }
    ) { innerPadding ->
        AppNavigation(navController = navController, mealViewModel = mealViewModel, supermarketViewModel = supermarketViewModel, innerPadding = innerPadding )
    }
}


