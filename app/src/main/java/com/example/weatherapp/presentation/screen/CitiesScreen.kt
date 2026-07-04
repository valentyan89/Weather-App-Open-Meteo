package com.example.weatherapp.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel

@Composable
fun CitiesScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val cities by viewModel.citiesListState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cities.sortedBy { it.name }) { city ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )

            ) {
                ListItem(
                    headlineContent = { Text(text = city.name) },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Город"
                        )
                    },
                    modifier = Modifier.clickable {
                        viewModel.selectCity(city.id)
                        navController.navigate(Screen.Weather.route) { popUpTo(0) }
                    }
                )
            }
        }
    }
}