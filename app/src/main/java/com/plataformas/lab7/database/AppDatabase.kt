package com.plataformas.lab7.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plataformas.lab7.database.categories.MealCategoryDao
import com.plataformas.lab7.database.categories.MealCategoryEntity
import com.plataformas.lab7.database.supermarket.SuperMarketItemDao
import com.plataformas.lab7.database.supermarket.SuperMarketItemEntity


@Database(entities = [MealCategoryEntity::class, SuperMarketItemEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealCategoryDao(): MealCategoryDao

    abstract fun superMarketItemDao(): SuperMarketItemDao
}