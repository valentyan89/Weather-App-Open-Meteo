package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.local.UserDataStore
import com.example.weatherapp.data.local.room.CityDatabase
import com.example.weatherapp.data.local.room.dao.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): CityDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            CityDatabase::class.java,
            "city.db"
        )
            .build()

    @Provides
    @Singleton
    fun provideCityDao(db: CityDatabase): CityDao = db.cityDao()

    @Provides
    @Singleton
    fun provideUserDataStore(@ApplicationContext context: Context): UserDataStore = UserDataStore(context)
}