package com.plataformas.lab7.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.plataformas.lab7.model.Recipe
import com.plataformas.lab7.viewmodel.RecipeViewModel

@Composable
fun RecipeScreen(navController: NavController, categoryName: String?) {
    if (categoryName != null) {
        Log.d("ARGUMENTS", categoryName)
    }

    val viewModel: RecipeViewModel = viewModel()
    val mealFilter by viewModel.meals.observeAsState(null)

    LaunchedEffect(Unit) {
        if (categoryName != null) {
            viewModel.fetchByCategory(categoryName)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Título de la pantalla
        Text(
            text = "Recetas de ${categoryName ?: "Categoría"}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            mealFilter?.let {
                items(it) { meal ->
                    RecipeItem(meal, navController)
                }
            }
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)  // Agregar padding entre los elementos
            .clickable {
                navController.navigate("recipe_detail_screen/${recipe.idMeal}")
            }.shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.medium,
                clip = true
            ),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de la receta
            Image(
                painter = rememberImagePainter(data = recipe.strMealThumb),
                contentDescription = recipe.strMeal,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = recipe.strMeal,
                    style = MaterialTheme.typography.titleSmall,  // Estilo más grande para el título
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "Ver más",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}
