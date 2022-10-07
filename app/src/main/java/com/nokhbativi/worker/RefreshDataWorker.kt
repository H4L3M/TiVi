package com.nokhbativi.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nokhbativi.database.getDatabase
import com.nokhbativi.repository.DataRepository
import retrofit2.HttpException

class RefreshDataWorker constructor(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {

        val database = getDatabase(applicationContext)
        val repository = DataRepository(database)
        return try {
            repository.refreshChannels()
            Log.d("EXT_TV", "-----------------------------------------------------")
            Result.success()
        } catch (e: HttpException) {
            Log.e("EXT_TV", "-------------------------ERROR-----------------------")
            Log.e("EXT_TV", "refresher : ${e.cause}")
            Result.retry()
        }
    }
}