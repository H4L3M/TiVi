package com.nokhbativi.repository

import com.nokhbativi.database.AppDatabase
import com.nokhbativi.dto.asDatabaseModel
import com.nokhbativi.model.network.FirestoreCategory
import com.nokhbativi.model.network.FirestoreChannel
import com.nokhbativi.network.RetroNet
import com.nokhbativi.util.CATEGORIES
import com.nokhbativi.util.CHANNELS
import com.nokhbativi.util.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val database: AppDatabase) {

    val liveEvents = database.appDao.getLiveEvents()

    fun categories(type: String) = database.appDao.getCategories(type = type)

    fun channels(code: String?) = database.appDao.getChannels(code = code)

    suspend fun refreshLiveEvents() {
        withContext(Dispatchers.IO) {
            database.appDao.deleteAllEvents()
            val liveEvents = RetroNet.networkSoccerApi.getLiveEvents()
            database.appDao.insertLiveEvents(*liveEvents.asDatabaseModel())
        }
    }
    suspend fun refreshCategories() {
        database.appDao.deleteAllCategories()
        get<FirestoreCategory>(CATEGORIES).collect { categories ->
            categories.forEach { category ->
                database.appDao.insertCategories(category.asDatabaseModel())
            }
        }
    }

    suspend fun refreshChannels() {
        database.appDao.deleteAllChannels()
        get<FirestoreChannel>(CHANNELS).collect { channels ->
            channels.forEach { channel ->
                database.appDao.insertChannels(channel.asDatabaseModel())
            }
        }
    }
}