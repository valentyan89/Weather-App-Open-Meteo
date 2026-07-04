package com.example.weatherapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.presentation.viewmodel.WeatherUiState
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val city by viewModel.weatherDetailState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val state = city) {
            is WeatherUiState.Loading -> CircularProgressIndicator()
            is WeatherUiState.Success -> {
                Text(
                    text = state.cities.find { it.id == state.selectedCity }?.name ?: "Город",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(Modifier.height(16.dp))

                Card(
                    modifier = Modifier,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Сегодня", style = MaterialTheme.typography.titleLarge)
                        Text(
                            "${state.weather.temperature.toInt()}°C",
                            style = MaterialTheme.typography.displayLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            state.weather.condition,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }

            is WeatherUiState.Error -> Text(
                "Ошибка: ${state.message}",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}