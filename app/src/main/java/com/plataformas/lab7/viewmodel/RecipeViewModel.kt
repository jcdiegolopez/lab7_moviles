package com.plataformas.lab7.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plataformas.lab7.model.Recipe
import com.plataformas.lab7.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel (val repository: RecipeRepository = RecipeRepository()): ViewModel() {

    private val recipe = MutableLiveData<List<Recipe>>()
    val meals: LiveData<List<Recipe>> = recipe

    fun fetchByCategory(categoryName: String) {
        viewModelScope.launch {
            try {
                val meals = repository.getRecipesByCategory(categoryName)
                recipe.value = meals
            } catch (e: Exception) {
                Log.e("RecipesViewModel", e.message.toString());
            }
        }
    }
}