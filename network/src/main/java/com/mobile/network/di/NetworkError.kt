package com.mobile.network.di

sealed interface NetworkError {

    data object NoInternet : NetworkError

    data object Timeout : NetworkError

    data object Unauthorized : NetworkError

    data object Forbidden : NetworkError

    data object NotFound : NetworkError

    data class HttpError(
        val code: Int,
        val message: String?
    ) : NetworkError

    data object Serialization : NetworkError

    data object EmptyBody : NetworkError

    data class Unknown(
        val throwable: Throwable
    ) : NetworkError
}