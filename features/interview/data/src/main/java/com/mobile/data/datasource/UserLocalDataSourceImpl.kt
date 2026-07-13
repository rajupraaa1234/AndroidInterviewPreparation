package com.mobile.data.datasource

import com.interview.domain.model.Users
import com.mobile.data.local.dao.UserDao
import com.mobile.data.mapper.toDomain
import com.mobile.data.mapper.toEntity
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val dao: UserDao
) : UserLocalDataSource {

    override suspend fun getUser(): List<Users> {
        return dao.getUser()?.toDomain().orEmpty()
    }

    override suspend fun saveUser(user: List<Users>) {
        dao.insert(user.toEntity())
    }
}