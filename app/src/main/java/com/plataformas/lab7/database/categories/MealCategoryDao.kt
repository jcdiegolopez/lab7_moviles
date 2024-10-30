package com.plataformas.lab7.database.categories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mealCategories: List<MealCategoryEntity>)

    @Query("SELECT * FROM categories")
    suspend fun getAllMealCategories(): List<MealCategoryEntity>
}