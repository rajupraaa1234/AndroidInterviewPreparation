package com.example.androidinterviewprepration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.interview.ui.composable.InterViewLayout
import com.interview.ui.mvvm.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue


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
    }
}

