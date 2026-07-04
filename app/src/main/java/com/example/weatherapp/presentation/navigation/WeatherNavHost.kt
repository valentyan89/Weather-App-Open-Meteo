package com.example.weatherapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.component.WeatherBottomBar
import com.example.weatherapp.presentation.screen.CitiesScreen
import com.example.weatherapp.presentation.screen.Screen
import com.example.weatherapp.presentation.screen.WeatherScreen

@Composable
fun WeatherNavHost() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { WeatherBottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Weather.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Weather.route) {
                WeatherScreen()
            }
            composable(Screen.Cities.route) {
                CitiesScreen(navController)
            }
        }
    }
}