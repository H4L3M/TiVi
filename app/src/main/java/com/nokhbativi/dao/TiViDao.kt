package com.nokhbativi.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.database.DatabaseCountry
import kotlinx.coroutines.flow.Flow

@Dao
interface TiViDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(vararg category: DatabaseCountry)

    @Query("SELECT * FROM countries WHERE visible == 1 ORDER BY id ASC")
    fun getCategories(): Flow<List<DatabaseCountry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChannels(vararg channels: DatabaseChannel)

    @Query("SELECT * FROM channels WHERE country == :code AND visible == 1 ORDER BY id ASC")
    fun getChannels(code: String?): Flow<List<DatabaseChannel>>
}