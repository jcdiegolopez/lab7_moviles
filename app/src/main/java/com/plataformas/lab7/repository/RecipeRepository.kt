package com.plataformas.lab7.repository

import com.plataformas.lab7.api.RetrofitInstance
import com.plataformas.lab7.model.Category
import com.plataformas.lab7.model.Recipe

class RecipeRepository(private val retrofitInstance: RetrofitInstance = RetrofitInstance()) {
    suspend fun getRecipesByCategory(categoryName: String): List<Recipe>  {
        return retrofitInstance.getMealsByCategory(categoryName).meals
    }
}