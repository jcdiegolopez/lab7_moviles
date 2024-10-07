package com.plataformas.lab7.api

import com.plataformas.lab7.model.MealsCategoriesResponse
import com.plataformas.lab7.model.RecipeDetail
import com.plataformas.lab7.model.RecipeDetailResponse
import com.plataformas.lab7.model.RecipeResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class RetrofitInstance {

    private lateinit var api: ApiService

    init {

        val retrofit =
            Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(ApiService::class.java)
    }


    suspend fun getMealsCategories(): MealsCategoriesResponse {
        return api.getCategories()
    }

    suspend fun getMealsByCategory(categoryName: String): RecipeResponse {
        return api.getRecipesByCategory(categoryName)
    }

    suspend fun getRecipeDetail(recipeId: String): RecipeDetailResponse {
        return api.getRecipeDetail(recipeId)
    }
}