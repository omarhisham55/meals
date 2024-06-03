package com.example.data.repo.netwrokRepos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.data.remote.ApiService
import com.example.domain.entites.mealEntity.Meal
import com.example.domain.entites.mealEntity.MealsList
import com.example.domain.repo.networkRepo.RandomMealRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomMealRepoImpl(private val apiService: ApiService) : RandomMealRepo {
    private var _randomMeal = MutableLiveData<Meal>()
    override suspend fun getRandomMealFromNetwork(): MutableLiveData<Meal> {
        apiService.getRandomMeal().enqueue(object : Callback<MealsList> {
            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                response.body()?.let {
                    _randomMeal.value = response.body()!!.meals[0]
                }
            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.e("zaza Random meals error", t.message.toString())
            }
        })
        return _randomMeal
    }
}