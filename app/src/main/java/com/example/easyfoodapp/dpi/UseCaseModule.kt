package com.example.easyfoodapp.dpi

import com.example.domain.repo.localRepo.LocalRepo
import com.example.domain.repo.networkRepo.CategoryRepo
import com.example.domain.repo.networkRepo.MealRepoById
import com.example.domain.repo.networkRepo.PopularItemRepo
import com.example.domain.repo.networkRepo.RandomMealRepo
import com.example.domain.usecase.localUseCases.LocalUseCase
import com.example.domain.usecase.networkUseCases.GetCategoryUseCase
import com.example.domain.usecase.networkUseCases.GetMealByIdUseCase
import com.example.domain.usecase.networkUseCases.GetRandomMealUseCase
import com.example.domain.usecase.networkUseCases.GetPopularMealsUseCase
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

    @Provides
    fun provideLocalUseCaseModule(localRepo: LocalRepo): LocalUseCase {
        return LocalUseCase(localRepo)
    }
}