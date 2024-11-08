package com.plataformas.lab7.repository


import com.plataformas.lab7.database.supermarket.SuperMarketItemDao
import com.plataformas.lab7.database.supermarket.SuperMarketItemEntity


class SupermarketRepository( private val supermarketDao: SuperMarketItemDao) {
    suspend fun getAllItems(): List<SuperMarketItemEntity> {
        return supermarketDao.getAllItems();
    }

    suspend fun insertItem(item: SuperMarketItemEntity) {
        supermarketDao.insertItem(item)
    }

    suspend fun deleteItem(itemId: String) {
        supermarketDao.deleteItem(itemId)
    }

    suspend fun updateItem(itemId: String, newQuantity: Int) {
        supermarketDao.updateItem(itemId, newQuantity)
    }

}