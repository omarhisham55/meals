package com.example.easyfoodapp.dpi

import com.example.data.local.db.MealDatabase
import com.example.data.remote.ApiService
import com.example.data.repo.localRepos.LocalRepoImpl
import com.example.data.repo.netwrokRepos.CategoryRepoImpl
import com.example.data.repo.netwrokRepos.MealRepoByIdImpl
import com.example.data.repo.netwrokRepos.PopularMealsRepoImpl
import com.example.data.repo.netwrokRepos.RandomMealRepoImpl
import com.example.domain.repo.localRepo.LocalRepo
import com.example.domain.repo.networkRepo.CategoryRepo
import com.example.domain.repo.networkRepo.MealRepoById
import com.example.domain.repo.networkRepo.PopularItemRepo
import com.example.domain.repo.networkRepo.RandomMealRepo
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

    @Provides
    fun provideLocalRepoModule(db: MealDatabase): LocalRepo {
        return LocalRepoImpl(db)
    }
}
