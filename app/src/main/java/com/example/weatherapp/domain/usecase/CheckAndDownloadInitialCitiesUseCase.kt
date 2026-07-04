package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.repository.WeatherRepository

class CheckAndDownloadInitialCitiesUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke() = repository.checkAndDownloadInitialCities()
}