package com.mobile.data.di

import com.interview.domain.repository.UserRepositoryWithRoom
import com.interview.domain.repository.UsersRepository
import com.mobile.data.local.datasource.UserLocalDataSource
import com.mobile.data.local.datasource.UserLocalDataSourceImpl
import com.mobile.data.repositoryImpl.UserRepositoryWithRoomImpl
import com.mobile.data.repositoryImpl.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository

    @Binds
    fun bindLocalDataSource(impl: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    fun bindUsersRepositoryWithRoom(impl: UserRepositoryWithRoomImpl): UserRepositoryWithRoom
}


