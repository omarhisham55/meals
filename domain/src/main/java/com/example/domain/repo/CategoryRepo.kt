package com.example.domain.repo

import com.example.domain.entites.categoryEntity.CategoryList
import retrofit2.Call

interface CategoryRepo {
    suspend fun getCategories() : Call<CategoryList>
}