package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.repository.WeatherRepository

class SaveSelectedCityUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityId: String) = repository.saveSelectedCity(cityId)
}