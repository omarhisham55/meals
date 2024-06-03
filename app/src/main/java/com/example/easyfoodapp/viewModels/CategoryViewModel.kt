package com.example.easyfoodapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory
import com.example.domain.usecase.networkUseCases.GetPopularMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryItemsUseCase: GetPopularMealsUseCase
) : ViewModel() {
    private var _categoryItems = MutableLiveData<List<MealsByCategory>>()
    val categoryItems: LiveData<List<MealsByCategory>> get() = _categoryItems

    fun getCategoryList(categoryName: String) = viewModelScope.launch {
        _categoryItems = getCategoryItemsUseCase(categoryName)
    }
}