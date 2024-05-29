package com.example.domain.repo

import com.example.domain.entites.mealEntity.MealsList
import retrofit2.Call

interface MealRepoById {
    suspend fun getMealById(id: String): Call<MealsList>
}