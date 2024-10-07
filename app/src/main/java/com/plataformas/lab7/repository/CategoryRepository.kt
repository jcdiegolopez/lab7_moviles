package com.plataformas.lab7.repository

import com.plataformas.lab7.api.ApiService
import com.plataformas.lab7.api.RetrofitInstance
import com.plataformas.lab7.model.Category

class CategoryRepository(private val retrofitInstance: RetrofitInstance = RetrofitInstance()) {
    suspend fun getCategories(): List<Category> {
        return retrofitInstance.getMealsCategories().categories
    }
}
