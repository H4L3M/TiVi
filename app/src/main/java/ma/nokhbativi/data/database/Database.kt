package ma.nokhbativi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ma.nokhbativi.data.dao.AppDao
import ma.nokhbativi.model.database.DatabaseCategory
import ma.nokhbativi.model.database.DatabaseChannel
import ma.nokhbativi.model.database.DatabaseFeaturedEvent
import ma.nokhbativi.model.database.DatabaseLiveEvent
import javax.inject.Singleton

@Database(
    entities = [
        DatabaseChannel::class,
        DatabaseCategory::class,
        DatabaseLiveEvent::class,
        DatabaseFeaturedEvent::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract val appDao: AppDao
}

@Volatile
private lateinit var INSTANCE: AppDatabase

@Singleton
fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "database"
            ).build()
        }
    }
    return INSTANCE
}