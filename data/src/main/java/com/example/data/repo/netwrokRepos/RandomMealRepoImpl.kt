package com.example.data.repo.netwrokRepos

import com.example.data.remote.ApiService
import com.example.domain.entites.mealEntity.MealsList
import com.example.domain.repo.networkRepo.RandomMealRepo
import retrofit2.Call

class RandomMealRepoImpl(private val apiService: ApiService) : RandomMealRepo {
    override suspend fun getRandomMealFromNetwork(): Call<MealsList> =
        apiService.getRandomMeal()
}