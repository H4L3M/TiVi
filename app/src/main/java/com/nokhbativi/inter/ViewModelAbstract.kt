package com.nokhbativi.inter

import com.nokhbativi.model.database.DatabaseCategory
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.database.DatabaseFeaturedEvent
import com.nokhbativi.model.database.DatabaseLiveEvent
import kotlinx.coroutines.flow.Flow

interface ViewModelAbstract {

    val liveEvents: Flow<List<DatabaseLiveEvent>>

    val featuredEvents: Flow<List<DatabaseFeaturedEvent>>

    val countries: Flow<List<DatabaseCategory>>

    val categories: Flow<List<DatabaseCategory>>

    val packages: Flow<List<DatabaseCategory>>

    fun channels(code: String): Flow<List<DatabaseChannel>>
}