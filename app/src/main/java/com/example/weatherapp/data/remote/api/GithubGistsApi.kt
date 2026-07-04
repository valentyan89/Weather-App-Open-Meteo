package com.example.weatherapp.data.remote.api

import com.example.weatherapp.data.model.CityDto
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubGistsApi {
    @GET
    suspend fun getInitialCities(@Url url: String): List<CityDto>
}