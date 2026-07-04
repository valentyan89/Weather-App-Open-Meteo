package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.local.room.entity.CityEntity
import com.example.weatherapp.data.model.CityDto
import com.example.weatherapp.domain.model.City

fun CityEntity.toDomain(): City{
    return City(
        id = this.id,
        name = this.name,
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun City.toEntity(): CityEntity {
    return CityEntity(
        id = this.id,
        name = this.name,
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun CityDto.toEntity(): CityEntity {
    return CityEntity(
        id = this.id,
        name = this.name,
        latitude = this.latitude.toDouble(),
        longitude = this.longitude.toDouble()
    )
}