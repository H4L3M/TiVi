package com.nokhbativi.repository

import android.util.Log
import com.nokhbativi.database.TiViDatabase
import com.nokhbativi.dto.asDatabaseModel
import com.nokhbativi.network.RetroNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val database: TiViDatabase) {

    val countries = database.dao.getCategories()

    fun channels(code: String?) = database.dao.getChannels(code = code)

    suspend fun refresh() {

        withContext(Dispatchers.IO) {

            try {
                val categories = RetroNet.networkTiViApi.getCountries()
                database.dao.insertCategories(*categories.asDatabaseModel())

                val channels = RetroNet.networkTiViApi.getChannels()
                database.dao.insertChannels(*channels)

            } catch (e: Exception) {
                Log.d("ELITE", e.message.toString())
            }
        }
    }
}