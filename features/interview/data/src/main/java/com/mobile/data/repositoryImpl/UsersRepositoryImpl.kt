package com.mobile.data.repositoryImpl

import com.interview.domain.model.UserInfoResult
import com.interview.domain.repository.UsersRepository
import com.mobile.data.mapper.mapToUserResult
import com.mobile.data.network.InterviewService
import com.mobile.network.di.NetworkResult
import com.mobile.network.di.safeApiCall
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val apiService: InterviewService,
) : UsersRepository {
    override suspend fun getUsers(): UserInfoResult {
        return getUsersFromNetwork()
    }

    private suspend fun getUsersFromNetwork(): UserInfoResult {
        return when (val result = safeApiCall { apiService.getUsers() }
        ) {
            is NetworkResult.Error -> UserInfoResult.Error(result.error.toString())
            is NetworkResult.Success -> mapToUserResult(response = result.data)
        }
    }
}