package ma.nokhbativi.data.repository

import ma.nokhbativi.data.database.AppDatabase
import ma.nokhbativi.data.dto.asDatabaseModel
import ma.nokhbativi.model.network.FirestoreCategory
import ma.nokhbativi.model.network.FirestoreChannel
import ma.nokhbativi.data.network.RetroNet
import ma.nokhbativi.util.CATEGORIES
import ma.nokhbativi.util.CHANNELS
import ma.nokhbativi.util.get
import ma.nokhbativi.util.today
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
            database.appDao.deleteAllEvents()
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