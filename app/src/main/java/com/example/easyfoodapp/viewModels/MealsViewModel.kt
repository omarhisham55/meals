package com.example.easyfoodapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entites.mealEntity.Meal
import com.example.domain.usecase.localUseCases.LocalUseCase
import com.example.domain.usecase.networkUseCases.GetMealByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealByIdUseCase: GetMealByIdUseCase, private val localUseCase: LocalUseCase
) : ViewModel() {
    private var _mealDetail = MutableLiveData<Meal>()
    val mealDetail: LiveData<Meal> get() = _mealDetail

    fun getMealById(id: String) = viewModelScope.launch {
        _mealDetail = getMealByIdUseCase(id)
    }

    fun addMealToFavorites(meal: Meal) {
        viewModelScope.launch {
            localUseCase.upsertMeal(meal)
        }
    }

    fun deleteMealFromFavorites(meal: Meal) {
        viewModelScope.launch {
            localUseCase.deleteMeal(meal)
        }
    }

}