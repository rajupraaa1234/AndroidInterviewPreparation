package com.mobile.network.di

import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> Response<T>
): NetworkResult<T> {

    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(NetworkError.EmptyBody)
            }
        } else {
            val error = when (response.code()) {
                401 -> NetworkError.Unauthorized
                403 -> NetworkError.Forbidden
                404 -> NetworkError.NotFound
                else -> NetworkError.HttpError(
                    response.code(),
                    response.message()
                )
            }
            NetworkResult.Error(error)
        }

    } catch (e: CancellationException) {
        throw e
    } catch (e: UnknownHostException) {
        NetworkResult.Error(NetworkError.NoInternet)
    }  catch (e: IOException) {
        NetworkResult.Error(NetworkError.NoInternet)
    } catch (e: Exception) {
        NetworkResult.Error(NetworkError.Unknown(e))
    }
}