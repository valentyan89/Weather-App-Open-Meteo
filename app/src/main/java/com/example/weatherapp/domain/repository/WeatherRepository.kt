package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): WeatherInfo
    fun getCities(): Flow<List<City>>
    fun getSelectedCity(): Flow<String?>
    suspend fun saveSelectedCity(cityId: String)
    suspend fun checkAndDownloadInitialCities()
}