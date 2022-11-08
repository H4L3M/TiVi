package com.nokhbativi.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nokhbativi.data.database.getDatabase
import com.nokhbativi.inter.ViewModelAbstract
import com.nokhbativi.model.database.DatabaseCategory
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.database.DatabaseFeaturedEvent
import com.nokhbativi.model.database.DatabaseLiveEvent
import com.nokhbativi.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application,
) : AndroidViewModel(app),
    ViewModelAbstract {

    private val database = getDatabase(app.applicationContext)
    val repository = DataRepository(database)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.refreshCategories()
            repository.refreshChannels()
            repository.refreshLiveEvents()
        }
    }

    override val liveEvents: Flow<List<DatabaseLiveEvent>>
        get() = repository.liveEvents

    override val featuredEvents: Flow<List<DatabaseFeaturedEvent>>
        get() = repository.featuredEvents

    override val countries: Flow<List<DatabaseCategory>>
        get() = repository.countries

    override val categories: Flow<List<DatabaseCategory>>
        get() = repository.categories

    override val packages: Flow<List<DatabaseCategory>>
        get() = repository.packages

    override fun channels(code: String): Flow<List<DatabaseChannel>> =
        repository.channels(code = code)
}