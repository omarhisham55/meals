package com.example.domain.repo

import com.example.domain.entites.mealEntity.MealsList
import retrofit2.Call

interface RandomMealRepo {
    suspend fun getRandomMealFromNetwork(): Call<MealsList>
}