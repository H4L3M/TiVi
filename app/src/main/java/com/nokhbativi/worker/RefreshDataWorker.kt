package com.nokhbativi.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nokhbativi.database.getDatabase
import com.nokhbativi.repository.DataRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RefreshDataWorker @Inject constructor(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {

        val database = getDatabase(applicationContext)
        val repository = DataRepository(database)
        return try {
            repository.refresh()
            Log.i("TiVi", "-----------------------------------------------------")
            Result.success()
        } catch (e: HttpException) {
            Log.e("TiVi", "-------------------------ERROR-----------------------")
            Log.e("TiVi", "refresher : ${e.cause}")
            Result.retry()
        }
    }
}