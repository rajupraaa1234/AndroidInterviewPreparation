package com.mobile.data.datasource

import com.interview.domain.model.Users

interface UserLocalDataSource {
    suspend fun getUser(): List<Users>?
    suspend fun saveUser(user: List<Users>)
}