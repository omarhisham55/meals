package com.example.data.repo.netwrokRepos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.data.remote.ApiService
import com.example.domain.entites.mealEntity.MealsList
import com.example.domain.repo.networkRepo.SearchRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchRepoImpl(private val apiService: ApiService) : SearchRepo {
    private var _searchList = MutableLiveData<MealsList>()

    override suspend fun getMealsBySearch(query: String): MutableLiveData<MealsList> {
        apiService.getMealyBySearch(query).enqueue(object : Callback<MealsList> {
            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                response.body()?.let { meals ->
                    _searchList.value = meals
                    Log.d("zaza search", _searchList.value?.meals?.size.toString())
                }
            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.e("zaza meal search error", t.message.toString())
            }
        })
        return _searchList
    }
}