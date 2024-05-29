package com.example.data.remote

import com.example.domain.entites.categoryEntity.CategoryList
import com.example.domain.entites.mealsByCategoryEntity.MealsByCategoryList
import com.example.domain.entites.mealEntity.MealsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("random.php")
    fun getRandomMeal(): Call<MealsList>

    @GET("lookup.php?")
    fun getMealById(@Query("i") id: String): Call<MealsList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories() : Call<CategoryList>
}