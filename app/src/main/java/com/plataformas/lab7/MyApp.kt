package com.plataformas.lab7

import android.app.Application
import androidx.room.Room
import com.plataformas.lab7.api.RetrofitInstance
import com.plataformas.lab7.database.AppDatabase
import com.plataformas.lab7.repository.CategoryRepository
import com.plataformas.lab7.repository.SupermarketRepository

class MyApp : Application() {

    // Singleton instance of the Room database
    private lateinit var database: AppDatabase
        private set

    lateinit var categoryRepository: CategoryRepository
        private set

    lateinit var supermarketRepository: SupermarketRepository
        private set

    lateinit var categoryWebService: RetrofitInstance
        private set

    override fun onCreate() {
        super.onCreate()

        categoryWebService = RetrofitInstance()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "meal-categories-db"
        ).fallbackToDestructiveMigration().build()

        categoryRepository = CategoryRepository(
            categoryWebService,
            database.mealCategoryDao()
        )

        supermarketRepository = SupermarketRepository(
            database.superMarketItemDao()
        )

    }
}