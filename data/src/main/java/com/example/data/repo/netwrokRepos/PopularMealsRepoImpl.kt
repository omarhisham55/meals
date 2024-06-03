package com.example.data.repo.netwrokRepos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.data.remote.ApiService
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategoryList
import com.example.domain.repo.networkRepo.PopularItemRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMealsRepoImpl(private val apiService: ApiService) : PopularItemRepo {
    private val _popularMeals = MutableLiveData<List<MealsByCategory>>()
    override suspend fun getPopularMeals(categoryName: String): MutableLiveData<List<MealsByCategory>> {
        apiService.getPopularItems(categoryName).enqueue(object : Callback<MealsByCategoryList> {
            override fun onResponse(
                call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>
            ) {
                response.body()?.let {
                    _popularMeals.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.e("zaza popular meals error", t.message.toString())
            }
        })
        return _popularMeals
    }
}