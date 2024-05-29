package com.example.domain.usecase

import com.example.domain.repo.MealRepoById

class GetMealByIdUseCase(private val mealByIdRepo: MealRepoById) {
    suspend operator fun invoke(id: String) = mealByIdRepo.getMealById(id)
}