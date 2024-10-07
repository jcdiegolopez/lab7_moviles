package com.plataformas.lab7.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.plataformas.lab7.model.RecipeDetail
import com.plataformas.lab7.viewmodel.RecipeDetailViewModel
import com.plataformas.lab7.viewmodel.RecipeViewModel

@Composable
fun RecipeDetailScreen(navController: NavController, recipeId: String?) {
    if (recipeId != null) {
        Log.d("ARGUMENTS", recipeId)
    }

    val viewModel: RecipeDetailViewModel = viewModel()
    val recipeDetail by viewModel.meal.observeAsState(null)

    LaunchedEffect(Unit) {
        if (recipeId != null) {
            viewModel.fetchByRecipeId(recipeId)
        }
    }

    if (recipeDetail == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        recipeDetail?.let { details ->
            RecipeDetailContent(details[0],navController)
        }
    }
}

@Composable
fun RecipeDetailContent(recipeDetail: RecipeDetail,navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Image(
                painter = rememberImagePainter(data = recipeDetail.strMealThumb),
                contentDescription = recipeDetail.strMeal,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = recipeDetail.strMeal,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "${recipeDetail.strCategory} | ${recipeDetail.strArea}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Instrucciones",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = recipeDetail.strInstructions,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ingredientes",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            val ingredients = listOf(
                recipeDetail.strIngredient1 to recipeDetail.strMeasure1,
                recipeDetail.strIngredient2 to recipeDetail.strMeasure2,
                recipeDetail.strIngredient3 to recipeDetail.strMeasure3,
                recipeDetail.strIngredient4 to recipeDetail.strMeasure4,
                recipeDetail.strIngredient5 to recipeDetail.strMeasure5,
                recipeDetail.strIngredient6 to recipeDetail.strMeasure6,
                recipeDetail.strIngredient7 to recipeDetail.strMeasure7,
                recipeDetail.strIngredient8 to recipeDetail.strMeasure8,
                recipeDetail.strIngredient9 to recipeDetail.strMeasure9,
                recipeDetail.strIngredient10 to recipeDetail.strMeasure10,
                recipeDetail.strIngredient11 to recipeDetail.strMeasure11,
                recipeDetail.strIngredient12 to recipeDetail.strMeasure12,
                recipeDetail.strIngredient13 to recipeDetail.strMeasure13,
                recipeDetail.strIngredient14 to recipeDetail.strMeasure14,
                recipeDetail.strIngredient15 to recipeDetail.strMeasure15,
                recipeDetail.strIngredient16 to recipeDetail.strMeasure16,
                recipeDetail.strIngredient17 to recipeDetail.strMeasure17,
                recipeDetail.strIngredient18 to recipeDetail.strMeasure18,
                recipeDetail.strIngredient19 to recipeDetail.strMeasure19,
                recipeDetail.strIngredient20 to recipeDetail.strMeasure20
            )

            ingredients.forEach { (ingredient, measure) ->
                if (!ingredient.isNullOrEmpty() && !measure.isNullOrEmpty()) {
                    Text(
                        text = "$ingredient - $measure",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

//            recipeDetail.strYoutube?.let { youtubeLink ->
//                if (youtubeLink.isNotEmpty()) {
//                    Text(
//                        text = "Ver en YouTube",
//                        color = Color.Blue,
//                        style = MaterialTheme.typography.bodyLarge,
//                        modifier = Modifier.clickable {
//                            navController.navigate("youtube_screen/$youtubeLink")
//                        }
//                    )
//                }
//            }
        }
    }
}
