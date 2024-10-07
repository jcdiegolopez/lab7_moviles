package com.plataformas.lab7.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plataformas.lab7.api.RetrofitInstance
import com.plataformas.lab7.model.Category
import com.plataformas.lab7.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository = CategoryRepository()) : ViewModel() {

    val categories : MutableState<List<Category>> = mutableStateOf(emptyList<Category>())
    var errorMessage: MutableState<String> = mutableStateOf("")

    init {
        Log.d("TAG_COROUTINES", "about to launch a coroutine")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("TAG_COROUTINES", "launching a coroutine")
                val meals = getCategories()
                Log.d("TAG_COROUTINES", "we have received sync data")
                print(meals)
                categories.value = meals
            }catch (e: Exception) {
                Log.e("TAG_COROUTINES", "Exception $e")
                errorMessage.value = e.message ?: "Unknown error"
            }

        }
        Log.d("TAG_COROUTINES", "other work")
    }

    // Función para obtener las categorías de la API
    private suspend fun getCategories() : List<Category> {
        return repository.getCategories()
    }
}
