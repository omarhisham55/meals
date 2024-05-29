package com.example.domain.repo

import com.example.domain.entites.mealsByCategoryEntity.MealsByCategoryList
import retrofit2.Call

interface PopularItemRepo {
    suspend fun getPopularMeals(categoryName:String) : Call<MealsByCategoryList>
}