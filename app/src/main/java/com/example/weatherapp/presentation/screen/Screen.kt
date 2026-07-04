package com.example.weatherapp.presentation.screen

sealed class Screen(val route: String) {
    object Cities : Screen("cities_screen")
    object Weather : Screen("weather_screen")
}