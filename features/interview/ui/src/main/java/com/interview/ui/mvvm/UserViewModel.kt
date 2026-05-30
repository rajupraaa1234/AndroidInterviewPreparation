package com.interview.ui.mvvm

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.domain.model.UserInfoResult
import com.interview.domain.usecase.GetUserUseCase
import com.interview.ui.state.UserInfo
import com.interview.ui.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserState())
    val uiState: StateFlow<UserState>
        get() = _uiState

    fun getUser() {
        viewModelScope.launch {
            when (val result = getUserUseCase()) {
                UserInfoResult.Error -> {
                    Log.d("UserInfoResult", "error")
                    _uiState.update { it.copy(isError = true) }
                }

                is UserInfoResult.Success -> {
                    Log.d("UserInfoResult", "getUser: ${result}")
                    val response = result.users.map {
                        with(it) {
                            UserInfo(
                                name = name,
                                company = company,
                                username = username,
                                email = email
                            )
                        }
                    }

                    _uiState.update {
                        it.copy(userList = response)
                    }
                }
            }
        }
    }
}