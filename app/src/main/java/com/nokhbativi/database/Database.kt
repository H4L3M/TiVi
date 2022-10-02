package com.nokhbativi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nokhbativi.dao.TiViDao
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.database.DatabaseCountry
import javax.inject.Singleton

@Database(
    entities = [
        DatabaseCountry::class,
        DatabaseChannel::class
    ],
    version = 1,
    exportSchema = false
)

abstract class TiViDatabase : RoomDatabase() {
    abstract val dao: TiViDao
}

@Volatile
private lateinit var INSTANCE: TiViDatabase

@Singleton
fun getDatabase(context: Context): TiViDatabase {
    synchronized(TiViDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                TiViDatabase::class.java,
                "TiVi"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}