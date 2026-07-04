package com.example.weatherapp.presentation.viewmodel

import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.WeatherInfo

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val weather: WeatherInfo, val cities: List<City>, val selectedCity: String?) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}