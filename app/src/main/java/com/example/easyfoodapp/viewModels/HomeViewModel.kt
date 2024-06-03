package com.example.easyfoodapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entites.categoryEntity.Category
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory
import com.example.domain.entites.mealEntity.Meal
import com.example.domain.entites.mealEntity.MealsList
import com.example.domain.usecase.localUseCases.LocalUseCase
import com.example.domain.usecase.networkUseCases.GetCategoryUseCase
import com.example.domain.usecase.networkUseCases.GetMealByIdUseCase
import com.example.domain.usecase.networkUseCases.GetMealBySearchUseCase
import com.example.domain.usecase.networkUseCases.GetPopularMealsUseCase
import com.example.domain.usecase.networkUseCases.GetRandomMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val getPopularMealsUseCase: GetPopularMealsUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getMealByIdUseCase: GetMealByIdUseCase,
    private val getMealBySearchUseCase: GetMealBySearchUseCase,
    localUseCase: LocalUseCase,
) : ViewModel() {
    private var _randomMeal = MutableLiveData<Meal>()
    private var _popularMeals = MutableLiveData<List<MealsByCategory>>()
    private var _categories = MutableLiveData<List<Category>>()
    private var _bottomSheetMeal = MutableLiveData<Meal>()
    private var _searchList = MutableLiveData<MealsList>()

    val randomMeal: LiveData<Meal> get() = _randomMeal
    val popularMeals: LiveData<List<MealsByCategory>> get() = _popularMeals
    val categories: LiveData<List<Category>> get() = _categories
    val bottomSheetMeal: LiveData<Meal> get() = _bottomSheetMeal
    val searchList: LiveData<MealsList> get() = _searchList

    //get favorite list
    private var _favoritesMeals = localUseCase.getMeals()

    val favoritesMeals: LiveData<List<Meal>> get() = _favoritesMeals


    //get random meal
    private var _saveStateRandomMeal: Meal? = null
    fun getRandomMeal() {
        viewModelScope.launch {
            _saveStateRandomMeal?.let {
                _randomMeal.postValue(it)
                return@let
            }
            _randomMeal = getRandomMealUseCase()
            _saveStateRandomMeal = _randomMeal.value
        }
    }

    //get popular meals
    private var _saveStatePopularMeals: List<MealsByCategory>? = null
    fun getPopularMeals(category: String = "Seafood") {
        viewModelScope.launch {
            _saveStatePopularMeals?.let {
                _popularMeals.postValue(it)
                return@let
            }
            _popularMeals = getPopularMealsUseCase(category)
            _saveStatePopularMeals = _popularMeals.value
        }
    }

    //get categories
    fun getCategories() = viewModelScope.launch {
        _categories = getCategoryUseCase()
    }

    //get meal by id
    fun getMealById(id: String) = viewModelScope.launch {
        _bottomSheetMeal = getMealByIdUseCase(id)
    }

    //get meal by search
    fun getMealsBySearch(query: String) = viewModelScope.launch {
        _searchList = getMealBySearchUseCase(query)
    }
}