package com.example.weatherapp.data.repository

import com.example.weatherapp.data.local.UserDataStore
import com.example.weatherapp.data.local.room.dao.CityDao
import com.example.weatherapp.data.local.room.entity.CityEntity
import com.example.weatherapp.data.mapper.toDomain
import com.example.weatherapp.data.mapper.toEntity
import com.example.weatherapp.data.mapper.toWeatherInfo
import com.example.weatherapp.data.remote.api.GithubGistsApi
import com.example.weatherapp.data.remote.api.WeatherApi
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.model.WeatherInfo
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val githubGistsApi: GithubGistsApi,
    private val dao: CityDao,
    private val dataStore: UserDataStore
) : WeatherRepository {
    override suspend fun getWeather(
        lat: Double,
        lon: Double
    ): WeatherInfo {
        val response = weatherApi.getWeatherData(lat, lon)
        return response.toWeatherInfo()
    }

    override fun getCities(): Flow<List<City>> {
        return dao.getAllCities().map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override fun getSelectedCity(): Flow<String?> {
        return dataStore.selectedCityId
    }

    override suspend fun saveSelectedCity(cityId: String) {
        dataStore.saveSelectedCityId(cityId)
    }

    override suspend fun checkAndDownloadInitialCities() {
        val emptyCitiesDatabase = dao.getAllCities().first().isEmpty()

        if (emptyCitiesDatabase){
            try {
                val url = "https://gist.githubusercontent.com/Stronger197/764f9886a1e8392ddcae2521437d5a3b/raw/65164ea1af958c75c81a7f0221bead610590448e/cities.json"
                val remoteCities = githubGistsApi.getInitialCities(url)
                val entities = remoteCities.map { it.toEntity() }
                val defaultCities = listOf(
                    CityEntity(
                        id = "Москва",
                        name = "Москва",
                        latitude = 55.7558,
                        longitude = 37.6173
                    )
                )
                dao.insertCities(defaultCities)
                dao.insertCities(entities)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}