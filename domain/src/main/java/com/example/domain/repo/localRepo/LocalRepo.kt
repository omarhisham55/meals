package com.example.domain.repo.localRepo

import androidx.lifecycle.LiveData
import com.example.domain.entites.mealEntity.Meal

interface LocalRepo {
    suspend fun upsertMeal(item: Meal)
    suspend fun deleteMeal(item: Meal)
    fun getMeals(): LiveData<List<Meal>>
}