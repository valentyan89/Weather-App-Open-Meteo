package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.WeatherDto
import com.example.weatherapp.domain.model.WeatherInfo

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        temperature = this.current.temperature,
        condition = parseWeatherCode(this.current.weatherCode)
    )
}

private fun parseWeatherCode(code: Int): String {
    return when (code) {
        0 -> "Ясно"
        1, 2, 3 -> "Переменная облачность"
        45, 48 -> "Туман"
        51, 53, 55 -> "Морось"
        61, 63, 65 -> "Дождь"
        71, 73, 75 -> "Снегопад"
        80, 81, 82 -> "Ливневый дождь"
        85, 86 -> "Ливневый снег"
        95, 96, 99 -> "Гроза"
        else -> "Неизвестно ($code)"
    }
}