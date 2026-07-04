package com.example.weatherapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.usecase.CheckAndDownloadInitialCitiesUseCase
import com.example.weatherapp.domain.usecase.GetCitiesUseCase
import com.example.weatherapp.domain.usecase.GetSelectedCityUseCase
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.domain.usecase.SaveSelectedCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val checkAndDownloadInitialCitiesUseCase: CheckAndDownloadInitialCitiesUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getSelectedCityUseCase: GetSelectedCityUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val saveSelectedCityUseCase: SaveSelectedCityUseCase
) : ViewModel() {
    val citiesListState: StateFlow<List<City>> = getCitiesUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val weatherDetailState: StateFlow<WeatherUiState> = getSelectedCityUseCase()
        .combine(citiesListState) { cityId, cities -> cityId to cities }
        .transformLatest { (cityId, cities) ->
            if (cities.isEmpty()) {
                emit(WeatherUiState.Loading)
                return@transformLatest
            }

            val currentId = cityId ?: cities.first().id
            emit(WeatherUiState.Loading)

            try {
                val city = cities.find { it.id == currentId }!!
                val weather = getWeatherUseCase(city.latitude, city.longitude)
                emit(WeatherUiState.Success(weather, cities, currentId))
            } catch (e: Exception) {
                emit(WeatherUiState.Error(e.message ?: "Ошибка"))
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WeatherUiState.Loading)

    fun selectCity(cityId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveSelectedCityUseCase(cityId)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO){
            Log.d("RRR", "скачиваем")
            checkAndDownloadInitialCitiesUseCase()
        }
    }

}