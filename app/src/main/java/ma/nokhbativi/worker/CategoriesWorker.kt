package ma.nokhbativi.worker

import android.content.Context
import android.util.Log.d
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ma.nokhbativi.data.database.getDatabase
import ma.nokhbativi.data.repository.DataRepository
import ma.nokhbativi.util.WORKER_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

@HiltWorker
class CategoriesWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(appContext = context.applicationContext, params = params) {
    override suspend fun doWork(): Result {

        val database = getDatabase(applicationContext)
        val repository = DataRepository(database)
        return try {
            repository.refreshCategories()
            d(WORKER_NAME, "CategoriesWorker -> Success")
            Result.success()
        } catch (e: HttpException) {
            d(WORKER_NAME, e.message())
            d(WORKER_NAME, "CategoriesWorker -> Failed")
            Result.retry()
        }
    }
}