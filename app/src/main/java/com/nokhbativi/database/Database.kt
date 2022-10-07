package com.nokhbativi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nokhbativi.dao.AppDao
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.database.DatabaseLiveEvent
import com.nokhbativi.model.database.DatabaseCategory
import javax.inject.Singleton

@Database(
    entities = [
        DatabaseCategory::class,
        DatabaseChannel::class,
        DatabaseLiveEvent::class
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
            )
//                .allowMainThreadQueries()
                .build()
        }
    }
    return INSTANCE
}