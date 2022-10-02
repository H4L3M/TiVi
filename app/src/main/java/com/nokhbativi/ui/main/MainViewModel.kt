package com.nokhbativi.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nokhbativi.database.getDatabase
import com.nokhbativi.inter.ViewModelAbstract
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(app: Application) : AndroidViewModel(app), ViewModelAbstract {

    private val database = getDatabase(app.applicationContext)
    private val repository = DataRepository(database)

    init {
        viewModelScope.launch {
            repository.refresh()
        }
    }

    val countries = repository.countries

    override fun channels(code: String?): Flow<List<DatabaseChannel>> = repository.channels(code = code)
}