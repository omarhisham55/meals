package com.example.data.repo.netwrokRepos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.data.remote.ApiService
import com.example.domain.entites.categoryEntity.Category
import com.example.domain.entites.categoryEntity.CategoryList
import com.example.domain.repo.networkRepo.CategoryRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepoImpl(private val apiService: ApiService) : CategoryRepo {
    private val _categories = MutableLiveData<List<Category>>()
    override suspend fun getCategories(): MutableLiveData<List<Category>> {
        apiService.getCategories().enqueue(object : Callback<CategoryList> {
            override fun onResponse(
                call: Call<CategoryList>, response: Response<CategoryList>
            ) {
                response.body()?.let {
                    _categories.value = response.body()!!.categories
                }
                return
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e("zaza popular meals error", t.message.toString())
            }
        })
        return _categories
    }
}
