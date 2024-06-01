package com.example.data.repo.netwrokRepos

import com.example.data.remote.ApiService
import com.example.domain.entites.mealEntity.MealsList
import com.example.domain.repo.networkRepo.MealRepoById
import retrofit2.Call

class MealRepoByIdImpl(private val apiService: ApiService) : MealRepoById {
    override suspend fun getMealById(id: String): Call<MealsList> = apiService.getMealById(id)
}