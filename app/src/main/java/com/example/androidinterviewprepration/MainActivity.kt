package com.example.androidinterviewprepration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.interview.ui.composable.InterViewLayout
import com.interview.ui.mvvm.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dynamicFeatureManager: DynamicFeatureManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: UserViewModel by viewModels()



        viewModel.viewModelScope.launch {
            viewModel.getUser()
        }
        enableEdgeToEdge()
        setContent {
            InterViewLayout(viewModel){
                installLoginFeature()
            }
        }
    }

    private fun installLoginFeature() {
        dynamicFeatureManager.install(
            moduleName = "dynamicfeature",
            onInstalled = {
                Log.d("installLoginFeature", "installLoginFeature: downloaded")
                openLogin()
            },
            onError = {
                Log.d("installLoginFeature", "installLoginFeature: error")

            }
        )
    }

    private fun openLogin() {
        val intent = Intent().apply {
            setClassName(
                this@MainActivity,
                "com.mobile.dynamicfeature.ui.compose.LoginActivity"
            )
        }

        startActivity(intent)
    }
}

