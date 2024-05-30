package com.example.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.DaoController
import com.example.data.local.MealTypeConverter
import com.example.domain.entites.mealEntity.Meal

@Database([Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): DaoController<Meal>

    companion object {
        @Volatile
        var instance: MealDatabase? = null

        @Synchronized
        fun getRoomInstance(context: Context): MealDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, MealDatabase::class.java, "meal.db")
                    .fallbackToDestructiveMigration().build()
            }
            return instance as MealDatabase
        }
    }
}