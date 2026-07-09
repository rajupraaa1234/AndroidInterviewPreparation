package com.interview.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.interview.ui.mvvm.UserViewModel
import com.interview.ui.state.UserInfo

@Composable
fun InterViewLayout(viewModel: UserViewModel) {
    val screeState = viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Build Variant -",
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(bottom = 20.dp, top = 20.dp)
                .background(color = Color.Green)
                .fillMaxWidth()
        )

        LazyColumn {
            items(screeState.value.userList) {
                UserItem(it)
            }
        }
    }
}

@Composable
fun UserItem(
    user: UserInfo
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = user.name)
            Text(text = user.email)
        }
    }
}
