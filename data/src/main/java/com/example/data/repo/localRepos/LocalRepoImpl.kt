package com.example.data.repo.localRepos

import com.example.data.local.db.MealDatabase
import com.example.domain.entites.mealEntity.Meal
import com.example.domain.repo.localRepo.LocalRepo

class LocalRepoImpl(private val db: MealDatabase) : LocalRepo {
    override suspend fun upsertMeal(item: Meal) = db.mealDao().upsertMeal(item)

    override suspend fun deleteMeal(item: Meal) = db.mealDao().deleteMeal(item)

    override fun getMeals() = db.mealDao().getMeals()
}