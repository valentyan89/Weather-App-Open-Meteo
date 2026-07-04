package com.example.weatherapp.di

import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.usecase.CheckAndDownloadInitialCitiesUseCase
import com.example.weatherapp.domain.usecase.GetCitiesUseCase
import com.example.weatherapp.domain.usecase.GetSelectedCityUseCase
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import com.example.weatherapp.domain.usecase.SaveSelectedCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideCheckAndDownloadInitialCitiesUseCase(repository: WeatherRepositoryImpl): CheckAndDownloadInitialCitiesUseCase =
        CheckAndDownloadInitialCitiesUseCase(repository)

    @Provides
    fun provideGetCitiesUseCase(repository: WeatherRepositoryImpl): GetCitiesUseCase =
        GetCitiesUseCase(repository)

    @Provides
    fun provideGetSelectedCitiesUseCase(repository: WeatherRepositoryImpl): GetSelectedCityUseCase =
        GetSelectedCityUseCase(repository)

    @Provides
    fun provideGetWeatherUseCase(repository: WeatherRepositoryImpl): GetWeatherUseCase =
        GetWeatherUseCase(repository)

    @Provides
    fun provideSaveSelectedCityUseCase(repository: WeatherRepositoryImpl): SaveSelectedCityUseCase =
        SaveSelectedCityUseCase(repository)
}