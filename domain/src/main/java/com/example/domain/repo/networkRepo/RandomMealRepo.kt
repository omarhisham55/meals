package com.example.domain.repo.networkRepo

import androidx.lifecycle.MutableLiveData
import com.example.domain.entites.mealEntity.Meal

interface RandomMealRepo {
    suspend fun getRandomMealFromNetwork(): MutableLiveData<Meal>
}