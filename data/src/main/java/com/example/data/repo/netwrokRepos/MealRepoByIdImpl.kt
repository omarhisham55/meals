package com.example.data.repo.netwrokRepos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.data.remote.ApiService
import com.example.domain.entites.mealEntity.Meal
import com.example.domain.entites.mealEntity.MealsList
import com.example.domain.repo.networkRepo.MealRepoById
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealRepoByIdImpl(private val apiService: ApiService) : MealRepoById {
    private val _meal = MutableLiveData<Meal>()
    override suspend fun getMealById(id: String): MutableLiveData<Meal> {
        apiService.getMealById(id).enqueue(object : Callback<MealsList> {
            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                if (response.body() != null) {
                    _meal.value = response.body()!!.meals[0]
                }
                return
            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.e("zaza meal id error", t.message.toString())
            }
        })
        return _meal
    }
}