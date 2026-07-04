package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetSelectedCityUseCase(private val repository: WeatherRepository) {
    operator fun invoke(): Flow<String?> = repository.getSelectedCity()
}