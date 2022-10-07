package com.nokhbativi.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.database.DatabaseLiveEvent
import com.nokhbativi.model.database.DatabaseCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(vararg categories: DatabaseCategory)

    @Query("SELECT * FROM Categories WHERE type == :type AND visible == 1 ORDER BY priority ASC")
    fun getCategories(type: String): Flow<List<DatabaseCategory>>

    @Query("DELETE FROM Categories")
    fun deleteAllCategories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChannels(vararg channels: DatabaseChannel)

    @Query("SELECT * FROM Channels WHERE country == :code AND visible == 1 ORDER BY priority ASC")
    fun getChannels(code: String?): Flow<List<DatabaseChannel>>

    @Query("DELETE FROM Channels")
    fun deleteAllChannels()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLiveEvents(vararg events: DatabaseLiveEvent)

    @Query("SELECT * FROM LiveEvents ORDER BY tournament_priority DESC")
    fun getLiveEvents(): Flow<List<DatabaseLiveEvent>>

    @Query("DELETE FROM LiveEvents")
    fun deleteAllEvents()
}