package com.example.easyfoodapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entites.mealEntity.Meal
import com.example.domain.entites.mealEntity.MealsList
import com.example.domain.usecase.GetMealByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealByIdUseCase: GetMealByIdUseCase
) : ViewModel() {
    private var _mealDetail = MutableLiveData<Meal>()
    fun getMealById(id: String) = viewModelScope.launch {
        getMealByIdUseCase(id).enqueue(object : Callback<MealsList> {
            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {
                if (response.body() != null) {
                    _mealDetail.value = response.body()!!.meals[0]
                }
                return
            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.e("zaza meal id error", t.message.toString())
            }
        })
    }

    fun observeMealById(): LiveData<Meal> = _mealDetail
}