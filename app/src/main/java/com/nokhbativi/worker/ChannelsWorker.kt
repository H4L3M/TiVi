package com.nokhbativi.worker

import android.content.Context
import android.util.Log.d
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nokhbativi.data.database.getDatabase
import com.nokhbativi.data.repository.DataRepository
import com.nokhbativi.util.WORKER_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

@HiltWorker
class ChannelsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(appContext = context.applicationContext, params = params) {
    override suspend fun doWork(): Result {

        val database = getDatabase(applicationContext)
        val repository = DataRepository(database)
        return try {
            repository.refreshChannels()
            d(WORKER_NAME, "ChannelsWorker -> Success")
            Result.success()
        } catch (e: HttpException) {
            d(WORKER_NAME, e.message())
            d(WORKER_NAME, "ChannelsWorker -> Failed")
            Result.retry()
        }
    }
}