package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.WeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(lat: Double, lon: Double): WeatherInfo = repository.getWeather(lat, lon)
}