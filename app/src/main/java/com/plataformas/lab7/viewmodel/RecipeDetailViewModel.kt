package com.plataformas.lab7.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plataformas.lab7.model.Recipe
import com.plataformas.lab7.model.RecipeDetail
import com.plataformas.lab7.repository.RecipeDetailRepository
import com.plataformas.lab7.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel (val repository: RecipeDetailRepository = RecipeDetailRepository()): ViewModel() {

    private val recipe = MutableLiveData<List<RecipeDetail>>()
    val meal: LiveData<List<RecipeDetail>> = recipe

    fun fetchByRecipeId(recipeId: String) {
        viewModelScope.launch {
            try {
                val meal = repository.getRecipeDetail(recipeId)
                recipe.value = meal
            } catch (e: Exception) {
                Log.e("RecipeDetailViewModel", e.message.toString());
            }
        }
    }
}