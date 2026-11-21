package com.mobile.data.workmanager

import android.content.Context
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.interview.domain.workmanager.SyncScheduler
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkManagerSyncSchedulerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SyncScheduler {
    override fun scheduleSync() {
        Log.d("UserViewModel", "Enqueueing SyncWorker")

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                10,
                TimeUnit.SECONDS
            )
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                "order_sync_work",
                ExistingWorkPolicy.REPLACE,
                request
            )

    }

//    override fun scheduleSync() {
//        Log.d("WorkManagerScheduler", "Enqueueing SyncWorker")
//
//        val request = OneTimeWorkRequestBuilder<SyncWorker>()
//            .build()
//
//        WorkManager.getInstance(context)
//            .enqueueUniqueWork(
//                "order_sync_work",
//                ExistingWorkPolicy.REPLACE,
//                request
//            )
//    }
}