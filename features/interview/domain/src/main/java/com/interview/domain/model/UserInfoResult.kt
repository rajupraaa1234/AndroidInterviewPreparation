package com.interview.domain.model

sealed interface UserInfoResult {
    data class Success(val users: List<Users>) : UserInfoResult
    data class Error(val error: String) : UserInfoResult

    data object NoInternet: UserInfoResult
}