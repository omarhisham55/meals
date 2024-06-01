package com.example.domain.usecase.networkUseCases

import com.example.domain.repo.networkRepo.RandomMealRepo

class GetRandomMealUseCase(private val randomMealRepo: RandomMealRepo) {
    suspend operator fun invoke() = randomMealRepo.getRandomMealFromNetwork()
}