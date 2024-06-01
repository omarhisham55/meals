package com.example.domain.repo.networkRepo

import com.example.domain.entites.mealEntity.MealsList
import retrofit2.Call

interface RandomMealRepo {
    suspend fun getRandomMealFromNetwork(): Call<MealsList>
}