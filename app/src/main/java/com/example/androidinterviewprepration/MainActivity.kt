package com.example.androidinterviewprepration

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.interview.ui.composable.InterViewLayout
import com.interview.ui.mvvm.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: UserViewModel by viewModels()
        viewModel.viewModelScope.launch {
            viewModel.getUser()
        }
        enableEdgeToEdge()
        setContent {
            InterViewLayout(viewModel)
        }

        WorkManager.getInstance(this)
            .getWorkInfosForUniqueWorkLiveData("order_sync_work")
            .observe(this) { workInfos ->

                workInfos.forEach { workInfo ->

                    Log.d(
                        "UserViewModel",
                        "Work ID: ${workInfo.id}"
                    )

                    Log.d(
                        "UserViewModel",
                        "State: ${workInfo.state}"
                    )

                    Log.d(
                        "UserViewModel",
                        "Stop reason: ${workInfo.stopReason}"
                    )
                }
            }
    }
}

