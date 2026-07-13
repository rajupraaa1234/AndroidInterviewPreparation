package com.interview.domain.repository

import com.interview.domain.model.UserInfoResult

interface UserRepositoryWithRoom {
    suspend fun getUsers(): UserInfoResult
}