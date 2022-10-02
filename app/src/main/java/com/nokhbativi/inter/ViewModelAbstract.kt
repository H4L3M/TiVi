package com.nokhbativi.inter

import com.nokhbativi.model.database.DatabaseChannel
import kotlinx.coroutines.flow.Flow

interface ViewModelAbstract {
    fun channels(code: String?): Flow<List<DatabaseChannel>>
}