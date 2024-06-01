package com.example.domain.usecase.networkUseCases

import com.example.domain.repo.networkRepo.CategoryRepo

class GetCategoryUseCase(private val categoryRepo: CategoryRepo) {
    suspend operator fun invoke() = categoryRepo.getCategories()
}