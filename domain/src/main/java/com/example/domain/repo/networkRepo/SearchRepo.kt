package com.example.domain.repo.networkRepo

import androidx.lifecycle.MutableLiveData
import com.example.domain.entites.mealEntity.MealsList

interface SearchRepo {
    suspend fun getMealsBySearch(query: String): MutableLiveData<MealsList>
}