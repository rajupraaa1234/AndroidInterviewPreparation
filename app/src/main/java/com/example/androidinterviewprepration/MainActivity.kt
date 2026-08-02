package com.example.androidinterviewprepration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.interview.ui.mvvm.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.w3c.dom.Text


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: UserViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            val searchText = viewModel.searchText.collectAsState()
            val person = viewModel.person.collectAsState()
            val isSearching = viewModel.isSearching.collectAsState()

            Column(modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxSize()
            ) {
                TextField(
                    value = searchText.value,
                    onValueChange = { viewModel.onSearchTextChange(it) },
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    placeholder = { Text("Search...") }
                )

                Spacer(Modifier.height(16.dp))

                if(isSearching.value){
                    Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }else{
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().weight(1f).padding(16.dp)) {
                        items(person.value){
                            Text(
                                text = "${it.firstName} ${it.secondName}",
                                modifier = Modifier
                                    .padding(vertical = 16.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

