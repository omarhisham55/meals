package com.example.domain.usecase.localUseCases

import com.example.domain.entites.mealEntity.Meal
import com.example.domain.repo.localRepo.LocalRepo

class LocalUseCase(private val localRepo: LocalRepo) {
    suspend fun upsertMeal(meal: Meal) = localRepo.upsertMeal(meal)
    suspend fun deleteMeal(meal: Meal) = localRepo.deleteMeal(meal)
    fun getMeals() = localRepo.getMeals()
}