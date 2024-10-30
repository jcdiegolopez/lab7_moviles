package com.plataformas.lab7.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.plataformas.lab7.ui.CategoryScreen
import com.plataformas.lab7.ui.RecipeDetailScreen
import com.plataformas.lab7.ui.RecipeScreen
import com.plataformas.lab7.viewmodel.CategoryViewModel

@Composable
fun AppNavigation(navController: NavHostController,  mealViewModel: CategoryViewModel,innerPadding : PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "category_screen",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("category_screen") {
            CategoryScreen(navController,viewModel = mealViewModel)
        }
        composable("recipe_screen/{categoryName}") { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            RecipeScreen(navController, categoryName)
        }
        composable("recipe_detail_screen/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            RecipeDetailScreen(navController, recipeId)
        }
    }
}