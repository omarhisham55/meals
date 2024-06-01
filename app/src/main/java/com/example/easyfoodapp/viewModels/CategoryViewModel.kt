package com.example.easyfoodapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategory
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategoryList
import com.example.domain.usecase.networkUseCases.GetPopularMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryItemsUseCase: GetPopularMealsUseCase
) : ViewModel() {
    private var _categoryItems = MutableLiveData<List<MealsByCategory>>()

    fun getCategoryList(categoryName: String) = viewModelScope.launch {
        getCategoryItemsUseCase.invoke(categoryName)
            .enqueue(object : Callback<MealsByCategoryList> {
                override fun onResponse(
                    call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>
                ) {
                    response.body()?.let {
                        _categoryItems.value = it.meals
                        Log.d("zaza category res", _categoryItems.value!!.size.toString())
                    }
                }
                override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                    Log.e("zaza category page error", t.message.toString())
                }

            })
    }

    fun observeItems(): LiveData<List<MealsByCategory>> = _categoryItems


}