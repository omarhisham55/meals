package com.example.domain.repo.networkRepo

import androidx.lifecycle.MutableLiveData
import com.example.domain.entites.categoryEntity.Category

interface CategoryRepo {
    suspend fun getCategories() : MutableLiveData<List<Category>>
}