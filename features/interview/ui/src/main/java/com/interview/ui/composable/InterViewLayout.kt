package com.interview.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.interview.ui.mvvm.UserViewModel
import com.interview.ui.state.UserInfo

@Composable
fun InterViewLayout(
    viewModel: UserViewModel,
    onLoinClick: ()-> Unit
) {
    val screeState = viewModel.uiState.collectAsState()
    if (screeState.value.isNoInternet) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = { },
            title = {
                Text("No Internet")
            },

            text = {
                Text("Please check your internet connection.")
            }
        )
    } else if (screeState.value.isLoading) {
        Loading()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(screeState.value.userList) {
                    UserItem(it){

                    }
                }
            }

            Button(
                onClick = onLoinClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Login dynamic feature")
            }
        }
    }
}

@Composable
private fun UserItem(
    user: UserInfo,
    onClick: ()-> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = user.name)
            Text(text = user.email)
        }
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
