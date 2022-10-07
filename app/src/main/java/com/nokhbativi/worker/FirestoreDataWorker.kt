package com.nokhbativi.worker

import android.content.Context
import android.util.Log.d
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nokhbativi.database.getDatabase
import com.nokhbativi.repository.DataRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

@HiltWorker
class FirestoreDataWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters
) :
    CoroutineWorker(appContext = context, params = params) {
    override suspend fun doWork(): Result {

        val database = getDatabase(applicationContext)
        val repository = DataRepository(database)
        return try {
            repository.refreshChannels()
            d(FIREBASE_WORKER, "Success")
            Result.success()
        } catch (e: HttpException) {
            d(FIREBASE_WORKER, e.message())
            Result.retry()
        }
    }

    companion object {
        const val FIREBASE_WORKER = "FirebaseWorker"
    }
}