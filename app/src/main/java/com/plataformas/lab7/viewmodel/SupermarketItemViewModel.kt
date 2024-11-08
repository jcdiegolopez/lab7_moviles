package com.plataformas.lab7.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.plataformas.lab7.database.categories.MealCategoryEntity
import com.plataformas.lab7.database.supermarket.SuperMarketItemEntity
import com.plataformas.lab7.repository.CategoryRepository
import com.plataformas.lab7.repository.SupermarketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException

class SupermarketViewModel(private val repository: SupermarketRepository) : ViewModel() {

    // Estado para la lista de artículos
    private val _items = MutableStateFlow<List<SuperMarketItemEntity>>(emptyList())
    val items: StateFlow<List<SuperMarketItemEntity>> = _items

    // Estado para manejar errores
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Función para cargar los artículos desde la base de datos
    fun loadItems() {
        viewModelScope.launch {
            try {
                _items.value = repository.getAllItems()
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    // Función para agregar un nuevo artículo
    fun addItem(itemName: String, quantity: Int, imagePath: String?) {
        val newItem = SuperMarketItemEntity(
            id = generateUniqueId(),
            itemName = itemName,
            quatity = quantity,
            imagePath = imagePath
        )
        viewModelScope.launch {
            try {
                repository.insertItem(newItem)
                loadItems() // Recargar la lista de artículos
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    // Función para eliminar un artículo por ID
    fun deleteItem(itemId: String) {
        viewModelScope.launch {
            try {
                repository.deleteItem(itemId)
                loadItems() // Recargar la lista de artículos
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    // Función para actualizar la cantidad de un artículo por ID
    fun updateItemQuantity(itemId: String, newQuantity: Int) {
        viewModelScope.launch {
            try {
                repository.updateItem(itemId, newQuantity)
                loadItems() // Recargar la lista de artículos
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    // Manejo de excepciones
    private fun handleException(exception: Exception) {
        when (exception) {
            is IOException -> _errorMessage.value = "Error de red: Verifica tu conexión a Internet."
            else -> _errorMessage.value = "Ocurrió un error inesperado: ${exception.localizedMessage}"
        }
    }

    // Generador de ID único (puedes personalizarlo según tus necesidades)
    private fun generateUniqueId(): String {
        return java.util.UUID.randomUUID().toString()
    }
}

// Factory para el ViewModel
class SupermarketViewModelFactory(private val repository: SupermarketRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SupermarketViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SupermarketViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase de ViewModel desconocida")
    }
}