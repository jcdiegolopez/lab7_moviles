package com.plataformas.lab7.repository

import com.plataformas.lab7.api.RetrofitInstance
import com.plataformas.lab7.model.Recipe
import com.plataformas.lab7.model.RecipeDetail

class RecipeDetailRepository(private val retrofitInstance: RetrofitInstance = RetrofitInstance()) {
    suspend fun getRecipeDetail(recipeId: String): List<RecipeDetail>  {
        return retrofitInstance.getRecipeDetail(recipeId).meals
    }
}