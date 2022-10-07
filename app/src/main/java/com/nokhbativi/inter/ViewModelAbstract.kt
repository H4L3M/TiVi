package com.nokhbativi.inter

import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.database.DatabaseLiveEvent
import com.nokhbativi.model.database.DatabaseCategory
import kotlinx.coroutines.flow.Flow

interface ViewModelAbstract {

    val liveEvents: Flow<List<DatabaseLiveEvent>>
    fun channels(code: String?): Flow<List<DatabaseChannel>>

    fun categories(type: String): Flow<List<DatabaseCategory>>
}