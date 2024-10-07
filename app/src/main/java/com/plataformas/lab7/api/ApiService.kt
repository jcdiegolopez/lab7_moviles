package com.plataformas.lab7.api

import com.plataformas.lab7.model.MealsCategoriesResponse
import com.plataformas.lab7.model.RecipeResponse
import com.plataformas.lab7.model.RecipeDetail
import com.plataformas.lab7.model.RecipeDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Endpoint para obtener todas las categorías
    @GET("categories.php")
    suspend fun getCategories(): MealsCategoriesResponse

    // Endpoint para obtener recetas por categoría
    @GET("filter.php")
    suspend fun getRecipesByCategory(@Query("c") categoryName: String): RecipeResponse

    // Endpoint para obtener los detalles de una receta específica
    @GET("lookup.php")
    suspend fun getRecipeDetail(@Query("i") recipeId: String): RecipeDetailResponse
}