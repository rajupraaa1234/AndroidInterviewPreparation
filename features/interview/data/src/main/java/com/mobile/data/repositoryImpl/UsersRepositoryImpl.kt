package com.mobile.data.repositoryImpl

import com.interview.domain.model.UserInfoResult
import com.interview.domain.model.Users
import com.interview.domain.repository.UsersRepository
import com.mobile.data.dto.UsersData
import com.mobile.data.network.InterviewService
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val apiService: InterviewService
) : UsersRepository {
    override suspend fun getUsers(): UserInfoResult {
        val response = apiService.getUsers()
        return if (response.isSuccessful) {
            mapToUserResult(response.body())
        } else {
            UserInfoResult.Error
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