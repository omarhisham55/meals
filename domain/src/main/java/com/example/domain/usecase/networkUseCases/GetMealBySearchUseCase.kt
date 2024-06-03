package com.example.domain.usecase.networkUseCases

import com.example.domain.repo.networkRepo.SearchRepo

class GetMealBySearchUseCase(private val searchRepo: SearchRepo) {
    suspend operator fun invoke(query: String) = searchRepo.getMealsBySearch(query)
}