package com.interview.domain.repository

import com.interview.domain.model.UserInfoResult

interface UsersRepository {
    suspend fun getUsers(): UserInfoResult
}