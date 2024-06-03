package com.example.domain.repo.networkRepo

import androidx.lifecycle.MutableLiveData
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory

interface PopularItemRepo {
    suspend fun getPopularMeals(categoryName:String) : MutableLiveData<List<MealsByCategory>>
}