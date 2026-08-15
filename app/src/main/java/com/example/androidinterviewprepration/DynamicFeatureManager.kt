package com.example.androidinterviewprepration

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

class DynamicFeatureManager(
    context: Context
) {

    private val splitInstallManager = SplitInstallManagerFactory.create(context)

    fun isInstalled(
        moduleName: String
    ): Boolean {
        return splitInstallManager
            .installedModules
            .contains(moduleName)
    }

    fun install(
        moduleName: String,
        onInstalled: () -> Unit,
        onError: (Exception) -> Unit
    ) {

        val request = SplitInstallRequest
            .newBuilder()
            .addModule(moduleName)
            .build()

        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener {
                onInstalled()
            }
            .addOnFailureListener {
                onError(it)
            }
    }
}