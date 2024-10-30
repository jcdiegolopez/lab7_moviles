package com.plataformas.lab7.database.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class MealCategoryEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,

    @ColumnInfo(name = "description")
    val description: String
)