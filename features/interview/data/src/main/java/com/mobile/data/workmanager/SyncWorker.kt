package com.mobile.data.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.interview.domain.usecase.SycnSchedulerUseCase

class SyncWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    private val useCase = SycnSchedulerUseCase()
    override suspend fun doWork(): Result {
        Log.d("UserViewModel", "doWork: useCase Called from SyncWorker")
        return try {
            Log.d("UserViewModel", "doWork: useCase Called from success SyncWorker")
            val message = useCase
            Log.d("UserViewModel", "doWork success: $message")
            Result.success()
        } catch (e: Exception) {
            Log.d("UserViewModel", "error: useCase Called from SyncWorker")
            Result.failure()
        }
    }
}