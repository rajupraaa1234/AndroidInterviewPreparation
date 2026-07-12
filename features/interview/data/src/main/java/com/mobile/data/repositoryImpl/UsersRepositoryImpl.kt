package com.mobile.data.repositoryImpl

import com.interview.domain.model.UserInfoResult
import com.interview.domain.model.Users
import com.interview.domain.repository.UsersRepository
import com.mobile.data.dto.UsersData
import com.mobile.data.network.InterviewService
import java.net.UnknownHostException
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val apiService: InterviewService
) : UsersRepository {
    override suspend fun getUsers(): UserInfoResult {
        return try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                mapToUserResult(response.body())
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