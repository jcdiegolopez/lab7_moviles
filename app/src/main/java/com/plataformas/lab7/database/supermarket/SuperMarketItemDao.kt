package com.plataformas.lab7.database.supermarket

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuperMarketItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(superMarketItem: SuperMarketItemEntity)

    @Query("SELECT * FROM supermarketitems")
    suspend fun getAllItems(): List<SuperMarketItemEntity>

    @Query("SELECT * FROM supermarketitems WHERE id = :itemId")
    suspend fun getItemById(itemId: String): SuperMarketItemEntity

    @Query("DELETE FROM supermarketitems WHERE id = :itemId")
    suspend fun deleteItem(itemId: String)

    @Query("UPDATE supermarketitems SET quatity = :newQuantity WHERE id = :itemId")
    suspend fun updateItem(itemId: String, newQuantity: Int)



}