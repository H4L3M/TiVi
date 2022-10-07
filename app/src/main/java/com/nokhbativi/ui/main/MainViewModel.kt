package com.nokhbativi.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nokhbativi.database.getDatabase
import com.nokhbativi.inter.ViewModelAbstract
import com.nokhbativi.model.database.DatabaseCategory
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.database.DatabaseLiveEvent
import com.nokhbativi.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(app: Application) : AndroidViewModel(app),
    ViewModelAbstract {

    private val database = getDatabase(app.applicationContext)
    val repository = DataRepository(database)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.refreshChannels()
            repository.refreshLiveEvents()
        }
    }

    override val liveEvents: Flow<List<DatabaseLiveEvent>> = repository.liveEvents

    override fun channels(code: String?): Flow<List<DatabaseChannel>> =
        repository.channels(code = code)

    override fun categories(type: String): Flow<List<DatabaseCategory>> =
        repository.categories(type = type)
}