package com.example.data.repo

import com.example.data.remote.ApiService
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategoryList
import com.example.domain.repo.PopularItemRepo
import retrofit2.Call

class PopularMealsRepoImpl(private val apiService: ApiService) : PopularItemRepo {
    override suspend fun getPopularMeals(categoryName: String): Call<MealsByCategoryList> =
        apiService.getPopularItems(categoryName)
}