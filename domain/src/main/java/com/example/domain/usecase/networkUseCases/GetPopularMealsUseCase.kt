package com.example.domain.usecase.networkUseCases

import com.example.domain.repo.networkRepo.PopularItemRepo

class GetPopularMealsUseCase(private val popularItemRepo: PopularItemRepo) {
    suspend operator fun invoke(categoryName: String) =
        popularItemRepo.getPopularMeals(categoryName)
}