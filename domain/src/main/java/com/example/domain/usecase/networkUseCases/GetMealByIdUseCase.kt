package com.example.domain.usecase.networkUseCases

import com.example.domain.repo.networkRepo.MealRepoById

class GetMealByIdUseCase(private val mealByIdRepo: MealRepoById) {
    suspend operator fun invoke(id: String) = mealByIdRepo.getMealById(id)
}