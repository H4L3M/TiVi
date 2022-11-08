package com.nokhbativi.data.repository

import com.nokhbativi.data.database.AppDatabase
import com.nokhbativi.data.dto.asDatabaseModel
import com.nokhbativi.model.network.FirestoreCategory
import com.nokhbativi.model.network.FirestoreChannel
import com.nokhbativi.data.network.RetroNet
import com.nokhbativi.util.CATEGORIES
import com.nokhbativi.util.CHANNELS
import com.nokhbativi.util.get
import com.nokhbativi.util.today
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val database: AppDatabase) {

    val liveEvents = database.appDao.getLiveEvents()
    val featuredEvents = database.appDao.getFeaturedEvents()

    val categories = database.appDao.getCategories()
    val countries = database.appDao.getCountries()
    val packages = database.appDao.getPackages()

    fun channels(code: String) = database.appDao.getChannels(code = code)

    suspend fun refreshLiveEvents() {
        withContext(Dispatchers.IO) {
            val liveEvents = RetroNet.networkSoccerApi.getLiveEvents()
            database.appDao.insertLiveEvents(*liveEvents.asDatabaseModel())
            val featuredEvents = RetroNet.networkSoccerApi.getFeaturedEvents(today.toString())
            database.appDao.insertFeaturedEvents(*featuredEvents.asDatabaseModel())
        }
    }

    suspend fun refreshCategories() {
        get<FirestoreCategory>(CATEGORIES).collect { categories ->
            categories.forEach { category ->
                database.appDao.insertCategories(category.asDatabaseModel())
            }
        }
    }

    suspend fun refreshChannels() {
        get<FirestoreChannel>(CHANNELS).collect { channels ->
            channels.forEach { channel ->
                database.appDao.insertChannels(channel.asDatabaseModel())
            }
        }
    }
}