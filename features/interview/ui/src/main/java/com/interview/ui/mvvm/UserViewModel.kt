package com.interview.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.domain.model.UserInfoResult
import com.interview.domain.network.NetworkMonitor
import com.interview.domain.usecase.GetUserUseCase
import com.interview.ui.state.UserInfo
import com.interview.ui.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserState())
    val uiState: StateFlow<UserState>
        get() = _uiState

    fun getUser() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            networkMonitor.isOnline.collectLatest { connected ->
                if (connected) {
                    when (val result = getUserUseCase()) {
                        UserInfoResult.Error -> onApiFailed()
                        is UserInfoResult.Success -> onFetchUserSuccess(result)
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            hasInternetConnectivity = false
                        )
                    }
                }
                _uiState.update {
                    it.copy(
                        hasInternetConnectivity = connected
                    )
                }
            }
        }
    }

    private fun onFetchUserSuccess(result: UserInfoResult.Success) {
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
            it.copy(
                isLoading = false,
                userList = response
            )
        }
    }

    private fun onApiFailed(){
        _uiState.update { it.copy(isError = true) }
        _uiState.update { it.copy(isLoading = false) }
    }
}