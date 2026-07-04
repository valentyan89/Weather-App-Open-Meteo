package com.example.weatherapp.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.presentation.screen.Screen

@Composable
fun WeatherBottomBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            selected = currentRoute == Screen.Weather.route,
            onClick = {
                navController.navigate(Screen.Weather.route) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Cloud, contentDescription = null) },
            label = { Text("Погода") }
        )


        NavigationBarItem(
            selected = currentRoute == Screen.Cities.route,
            onClick = {
                navController.navigate(Screen.Cities.route) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
            label = { Text("Города") }
        )
    }
}