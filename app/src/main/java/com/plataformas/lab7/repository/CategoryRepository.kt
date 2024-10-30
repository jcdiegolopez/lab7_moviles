package com.plataformas.lab7.repository

import com.plataformas.lab7.api.ApiService
import com.plataformas.lab7.api.IMealsWebService
import com.plataformas.lab7.api.RetrofitInstance
import com.plataformas.lab7.database.categories.MealCategoryDao
import com.plataformas.lab7.database.categories.MealCategoryEntity
import com.plataformas.lab7.model.Category
import com.plataformas.lab7.model.toEntity

class CategoryRepository(private val webService: IMealsWebService,private val mealCategoryDao: MealCategoryDao) {
    suspend fun getCategories(): List<MealCategoryEntity> {
        val entities = webService.getMealsCategories().categories
        val content = entities.map { it.toEntity() }
        mealCategoryDao.insertAll(content)
        return mealCategoryDao.getAllMealCategories()
    }
}
