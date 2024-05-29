package com.example.domain.usecase

import com.example.domain.repo.CategoryRepo

class GetCategoryUseCase(private val categoryRepo: CategoryRepo) {
    suspend operator fun invoke() = categoryRepo.getCategories()
}