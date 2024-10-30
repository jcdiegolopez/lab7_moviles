package com.plataformas.lab7.model

import com.google.gson.annotations.SerializedName
import com.plataformas.lab7.database.categories.MealCategoryEntity

data class Category(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String?,
    @SerializedName("strCategoryDescription") val description: String,
    @SerializedName("strCategoryThumb") val imageUrl: String
)

fun Category.toEntity(): MealCategoryEntity {
    return MealCategoryEntity(
        id = this.id,
        name = this.name ?: "",
        imageUrl = this.imageUrl,
        description = this.description
    )
}