package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetCitiesUseCase(private val repository: WeatherRepository) {
    operator fun invoke(): Flow<List<City>> = repository.getCities()
}