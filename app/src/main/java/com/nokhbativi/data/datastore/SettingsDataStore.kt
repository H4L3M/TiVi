package com.nokhbativi.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


val DARK_MODE = booleanPreferencesKey("DarkMode")
fun readSettings(context: Context) = context.dataStore.data
    .map { preferences ->
        // No type safety.
        preferences[DARK_MODE] ?: false
    }

suspend fun setDarkMode(context: Context, isDark: Boolean) {
    context.dataStore.edit { settings ->
        settings[DARK_MODE] = isDark
    }
}