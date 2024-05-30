package com.example.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//@Target(AnnotationTarget.CLASS)
//@Retention(AnnotationRetention.BINARY)
//annotation class TableName(val name: String)

@Dao
interface DaoController<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(item: T)

    @Delete
    suspend fun deleteMeal(item: T)

    @Query(value = "select * from (select :tableName)")
    fun getMealsFromDatabase(tableName: String): LiveData<List<T>>
}