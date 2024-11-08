package com.plataformas.lab7.database.supermarket

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supermarketitems")
data class SuperMarketItemEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey val id: String,

    @ColumnInfo(name = "itemName")
    val itemName: String,

    @ColumnInfo(name = "quatity")
    val quatity: Int,

    @ColumnInfo(name = "imagePath")
    val imagePath: String?
)