package com.nokhbativi.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.nokhbativi.model.database.DatabaseLiveEvent

@Dao
interface EventsDao {

    @Query("SELECT * FROM LiveEvents")
    fun getEvents(): PagingSource<Int, DatabaseLiveEvent>
}