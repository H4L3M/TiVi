package com.nokhbativi

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.nokhbativi.worker.CategoriesWorker
import com.nokhbativi.worker.ChannelsWorker
import com.nokhbativi.worker.LiveEventsWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class TiVi : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()

        WorkManager.getInstance(this).enqueue(OneTimeWorkRequest.from(LiveEventsWorker::class.java))
        WorkManager.getInstance(this).enqueue(OneTimeWorkRequest.from(CategoriesWorker::class.java))
        WorkManager.getInstance(this).enqueue(OneTimeWorkRequest.from(ChannelsWorker::class.java))
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}