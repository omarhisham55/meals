package com.example.domain.repo.networkRepo

import androidx.lifecycle.MutableLiveData
import com.example.domain.entites.mealEntity.Meal

interface MealRepoById {
    suspend fun getMealById(id: String): MutableLiveData<Meal>
}