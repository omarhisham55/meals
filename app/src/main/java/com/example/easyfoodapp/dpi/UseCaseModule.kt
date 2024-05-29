package com.example.easyfoodapp.dpi

import com.example.domain.repo.CategoryRepo
import com.example.domain.repo.MealRepoById
import com.example.domain.repo.PopularItemRepo
import com.example.domain.repo.RandomMealRepo
import com.example.domain.usecase.GetCategoryUseCase
import com.example.domain.usecase.GetMealByIdUseCase
import com.example.domain.usecase.GetRandomMealUseCase
import com.example.domain.usecase.GetPopularMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCaseModule(randomMealRepo: RandomMealRepo): GetRandomMealUseCase {
        return GetRandomMealUseCase(randomMealRepo)
    }

    @Provides
    fun provideUseCaseByIdModule(mealRepoById: MealRepoById): GetMealByIdUseCase {
        return GetMealByIdUseCase(mealRepoById)
    }

    @Provides
    fun provideUseCasePopularMealsModule(popularItemRepo: PopularItemRepo): GetPopularMealsUseCase {
        return GetPopularMealsUseCase(popularItemRepo)
    }

    @Provides
    fun provideUseCaseCategoriesModule(categoryRepo: CategoryRepo): GetCategoryUseCase {
        return GetCategoryUseCase(categoryRepo)
    }
}