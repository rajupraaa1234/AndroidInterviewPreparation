package com.interview.ui.mvvm

import android.util.Log
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
import kotlin.collections.copy

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserState())
    val uiState: StateFlow<UserState>
        get() = _uiState

    fun getUser() {
        setLoadingStatus(true)
        viewModelScope.launch {
            when (val result = getUserUseCase()) {
                UserInfoResult.Error -> onError()
                is UserInfoResult.Success -> onSuccess(result)
                UserInfoResult.NoInternet -> onConnectionFailed()
            }
        }
    }

    fun onItemClick() {
        viewModelScope.launch {
            val result = getUserUseCase()
            Log.d("UsersRepositoryImpl", "user data onItemClick: $result")
        }
    }

    private fun onSuccess(result: UserInfoResult.Success) {
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
        setLoadingStatus(false)
    }

    private fun onError() {
        _uiState.update { it.copy(isError = true) }
        setLoadingStatus(false)
    }

    private fun onConnectionFailed() {
        _uiState.update { it.copy(isNoInternet = true) }
        setLoadingStatus(false)
    }

    private fun setLoadingStatus(status: Boolean) {
        _uiState.update { it.copy(isLoading = status) }
    }
}

