package ma.nokhbativi.inter

import ma.nokhbativi.model.database.DatabaseCategory
import ma.nokhbativi.model.database.DatabaseChannel
import ma.nokhbativi.model.database.DatabaseFeaturedEvent
import ma.nokhbativi.model.database.DatabaseLiveEvent
import kotlinx.coroutines.flow.Flow

interface ViewModelAbstract {

    val liveEvents: Flow<List<DatabaseLiveEvent>>

    val featuredEvents: Flow<List<DatabaseFeaturedEvent>>

    val countries: Flow<List<DatabaseCategory>>

    val categories: Flow<List<DatabaseCategory>>

    val packages: Flow<List<DatabaseCategory>>

    fun channels(code: String): Flow<List<DatabaseChannel>>
}