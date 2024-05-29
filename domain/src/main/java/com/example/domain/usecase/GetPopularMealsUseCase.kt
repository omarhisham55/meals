package com.example.domain.usecase

import com.example.domain.repo.PopularItemRepo

class GetPopularMealsUseCase(private val popularItemRepo: PopularItemRepo) {
    suspend operator fun invoke(categoryName: String) =
        popularItemRepo.getPopularMeals(categoryName)
}