package com.mobile.data.repositoryImpl

import android.util.Log
import com.interview.domain.model.UserInfoResult
import com.interview.domain.model.Users
import com.interview.domain.repository.UsersRepository
import com.mobile.core_cache.cache.MemoryCache
import com.mobile.data.dto.UsersData
import com.mobile.data.network.InterviewService
import java.net.UnknownHostException
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val apiService: InterviewService,
    private val memoryCache: MemoryCache
) : UsersRepository {
    override suspend fun getUsers(): UserInfoResult {
        val response = memoryCache.get<UserInfoResult>(USER_CACHE_DATA)
        return response ?: getUsersFromNetwork()
    }

    private suspend fun getUsersFromNetwork(): UserInfoResult {
        return try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                val mappedData = mapToUserResult(response.body())
                memoryCache.put(USER_CACHE_DATA, mappedData)
                mappedData
            } else {
                UserInfoResult.Error
            }
        } catch (e: UnknownHostException) {
            UserInfoResult.NoInternet
        }
    }

    private fun mapToUserResult(response: List<UsersData>?): UserInfoResult {
        if (response != null) {
            return UserInfoResult.Success(
                response.map {
                    with(it) {
                        Users(
                            id = id,
                            name = name,
                            company = company,
                            username = username,
                            email = email,
                            address = address,
                            zip = zip,
                            state = state,
                            country = country,
                            phone = phone,
                            photo = photo
                        )
                    }
                }
            )
        }
        return UserInfoResult.Error
    }

    private companion object {
        const val USER_CACHE_DATA = "USER_CACHE_DATA"
    }
}