package com.example.weatherapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "weather_settings")

@Singleton
class UserDataStore @Inject constructor(
    private val context: Context
) {
    companion object {
        private val SELECTED_CITY_ID_KEY = stringPreferencesKey("selected_city_id")
    }

    val selectedCityId: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[SELECTED_CITY_ID_KEY]
        }

    suspend fun saveSelectedCityId(cityId: String) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CITY_ID_KEY] = cityId
        }
    }
}