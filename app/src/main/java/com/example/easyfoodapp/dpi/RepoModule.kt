package com.example.easyfoodapp.dpi

import com.example.data.remote.ApiService
import com.example.data.repo.CategoryRepoImpl
import com.example.data.repo.MealRepoByIdImpl
import com.example.data.repo.PopularMealsRepoImpl
import com.example.data.repo.RandomMealRepoImpl
import com.example.domain.repo.CategoryRepo
import com.example.domain.repo.MealRepoById
import com.example.domain.repo.PopularItemRepo
import com.example.domain.repo.RandomMealRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRandomRepoModule(apiService: ApiService): RandomMealRepo {
        return RandomMealRepoImpl(apiService)
    }

    @Provides
    fun provideRepoModuleById(apiService: ApiService): MealRepoById {
        return MealRepoByIdImpl(apiService)
    }

    @Provides
    fun provideRepoModulePopularMeals(apiService: ApiService): PopularItemRepo {
        return PopularMealsRepoImpl(apiService)
    }

    @Provides
    fun provideRepoModuleCategory(apiService: ApiService): CategoryRepo {
        return CategoryRepoImpl(apiService)
    }
}
