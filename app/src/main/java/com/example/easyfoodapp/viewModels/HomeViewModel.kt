package com.example.easyfoodapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entites.categoryEntity.Category
import com.example.domain.entites.categoryEntity.CategoryList
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategoryList
import com.example.domain.entites.mealEntity.Meal
import com.example.domain.entites.mealEntity.MealsList
import com.example.domain.usecase.localUseCases.LocalUseCase
import com.example.domain.usecase.networkUseCases.GetCategoryUseCase
import com.example.domain.usecase.networkUseCases.GetPopularMealsUseCase
import com.example.domain.usecase.networkUseCases.GetRandomMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val getPopularMealsUseCase: GetPopularMealsUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val localUseCase: LocalUseCase,
) : ViewModel() {
    //get random meal
    private var _randomMeal = MutableLiveData<Meal>()
    fun getRandomMeal() = viewModelScope.launch {
        try {
            getRandomMealUseCase().enqueue(object : Callback<MealsList> {
                override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                    response.body()?.let {
                        _randomMeal.value = response.body()!!.meals[0]
                    }
                    return
                }

                override fun onFailure(call: Call<MealsList>, t: Throwable) {
                    Log.e("zaza Random meals error", t.message.toString())
                }
            })
        } catch (e: Exception) {
            Log.e("zaza Error ViewModel", e.message.toString())
        }
    }

    fun observeRandomMeal(): LiveData<Meal> = _randomMeal

    //get popular meals
    private var _popularMeals = MutableLiveData<List<MealsByCategory>>()
    fun getPopularMeals(name: String = "Seafood") = viewModelScope.launch {
        getPopularMealsUseCase(name).enqueue(object : Callback<MealsByCategoryList> {
            override fun onResponse(
                call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>
            ) {
                response.body()?.let {
                    _popularMeals.value = response.body()!!.meals
                }
                return
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.e("zaza popular meals error", t.message.toString())
            }
        })
    }

    fun observePopularMeals(): LiveData<List<MealsByCategory>> = _popularMeals

    //get categories
    private var _categories = MutableLiveData<List<Category>>()
    fun getCategories() = viewModelScope.launch {
        getCategoryUseCase().enqueue(object : Callback<CategoryList> {
            override fun onResponse(
                call: Call<CategoryList>, response: Response<CategoryList>
            ) {
                response.body()?.let {
                    _categories.value = response.body()!!.categories
                }
                return
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e("zaza popular meals error", t.message.toString())
            }
        })
    }

    fun observeCategories(): LiveData<List<Category>> = _categories

    //get favorite list
    private var _favoritesMeals = localUseCase.getMeals()

    fun observeFavMeals(): LiveData<List<Meal>> = _favoritesMeals


}