package ma.nokhbativi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ma.nokhbativi.model.database.DatabaseCategory
import ma.nokhbativi.model.database.DatabaseChannel
import ma.nokhbativi.model.database.DatabaseFeaturedEvent
import ma.nokhbativi.model.database.DatabaseLiveEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(vararg categories: DatabaseCategory)

    @Query("SELECT * FROM Categories WHERE type == 'CAT' AND visible == 1 ORDER BY priority ASC")
    fun getCategories(): Flow<List<DatabaseCategory>>

    @Query("SELECT * FROM Categories WHERE type == 'PAC' AND visible == 1 ORDER BY priority ASC")
    fun getPackages(): Flow<List<DatabaseCategory>>

    @Query("SELECT * FROM Categories WHERE type == 'COU' AND visible == 1 ORDER BY priority ASC")
    fun getCountries(): Flow<List<DatabaseCategory>>

    @Query("DELETE FROM Categories")
    fun deleteAllCategories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChannels(vararg channels: DatabaseChannel)

    @Query("SELECT * FROM Channels WHERE country == :code OR category == :code OR package == :code AND visible == 1 ORDER BY priority ASC")
    fun getChannels(code: String): Flow<List<DatabaseChannel>>

    @Query("DELETE FROM Channels")
    fun deleteAllChannels()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLiveEvents(vararg events: DatabaseLiveEvent)

    @Query("SELECT * FROM LiveEvents ORDER BY tournament_priority DESC")
    fun getLiveEvents(): Flow<List<DatabaseLiveEvent>>

    @Query("DELETE FROM LiveEvents")
    fun deleteAllEvents()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeaturedEvents(vararg events: DatabaseFeaturedEvent)

    @Query("SELECT * FROM FeaturedEvents")
    fun getFeaturedEvents(): Flow<List<DatabaseFeaturedEvent>>

    @Query("DELETE FROM FeaturedEvents")
    fun deleteAllFeaturedEvents()
}