package com.example.data.repo.netwrokRepos

import com.example.data.remote.ApiService
import com.example.domain.entites.categoryEntity.CategoryList
import com.example.domain.repo.networkRepo.CategoryRepo
import retrofit2.Call

class CategoryRepoImpl(private val apiService: ApiService) : CategoryRepo {
    override suspend fun getCategories(): Call<CategoryList> = apiService.getCategories()
}