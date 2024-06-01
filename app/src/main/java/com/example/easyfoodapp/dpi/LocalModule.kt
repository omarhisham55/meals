package com.example.easyfoodapp.dpi

import android.content.Context
import com.example.data.local.db.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): MealDatabase {
        return MealDatabase.getRoomInstance(context)
    }

}