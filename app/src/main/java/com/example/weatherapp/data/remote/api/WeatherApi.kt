package com.example.weatherapp.data.remote.api

import com.example.weatherapp.data.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current") current: String = "temperature_2m,relative_humidity_2m,weather_code",
        @Query("hourly") hourly: String = "temperature_2m"
    ): WeatherDto
}