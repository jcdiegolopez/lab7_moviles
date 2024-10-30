package com.plataformas.lab7.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plataformas.lab7.database.categories.MealCategoryDao
import com.plataformas.lab7.database.categories.MealCategoryEntity


@Database(entities = [MealCategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealCategoryDao(): MealCategoryDao
}