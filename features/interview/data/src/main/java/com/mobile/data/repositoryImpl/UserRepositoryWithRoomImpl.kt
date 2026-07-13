package com.mobile.data.repositoryImpl

import com.interview.domain.model.UserInfoResult
import com.interview.domain.model.Users
import com.interview.domain.repository.UserRepositoryWithRoom
import com.mobile.data.local.datasource.UserLocalDataSource
import com.mobile.data.dto.UsersData
import com.mobile.data.network.InterviewService
import java.net.UnknownHostException
import javax.inject.Inject

class UserRepositoryWithRoomImpl @Inject constructor(
    private val apiService: InterviewService,
    private val roomDatabase: UserLocalDataSource
) : UserRepositoryWithRoom {

    override suspend fun getUsers(): UserInfoResult {
        val response = roomDatabase.getUser()
        return if (!response.isNullOrEmpty()) {
            UserInfoResult.Success(users = response)
        } else {
            getUsersFromNetwork()
        }
    }

    private suspend fun getUsersFromNetwork(): UserInfoResult {
        return try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                val mappedData = mapToUserResult(response.body())
                if (mappedData is UserInfoResult.Success) {
                    roomDatabase.saveUser(mappedData.users)
                }
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
}