package com.interview.domain.model

sealed interface UserInfoResult {
    data class Success(val users: List<Users>) : UserInfoResult
    data object Error : UserInfoResult

    data object NoInternet: UserInfoResult
}