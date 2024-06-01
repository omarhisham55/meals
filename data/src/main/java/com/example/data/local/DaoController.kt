package com.example.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entites.mealEntity.Meal

//@Target(AnnotationTarget.CLASS)
//@Retention(AnnotationRetention.BINARY)
//annotation class TableName(val name: String)

@Dao
interface DaoController {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(item: Meal)

    @Delete
    suspend fun deleteMeal(item: Meal)

    @Query(value = "select * from mealInformation")
    fun getMeals(): LiveData<List<Meal>>
}