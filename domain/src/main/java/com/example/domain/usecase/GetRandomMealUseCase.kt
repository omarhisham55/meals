package com.example.domain.usecase

import com.example.domain.repo.RandomMealRepo

class GetRandomMealUseCase(private val randomMealRepo: RandomMealRepo) {
    suspend operator fun invoke() = randomMealRepo.getRandomMealFromNetwork()
}