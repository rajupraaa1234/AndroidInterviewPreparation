package com.mobile.data.di

import com.interview.domain.repository.UserRepositoryWithRoom
import com.interview.domain.repository.UsersRepository
import com.mobile.data.datasource.UserLocalDataSource
import com.mobile.data.datasource.UserLocalDataSourceImpl
import com.mobile.data.network.InterviewService
import com.mobile.data.repositoryImpl.UserRepositoryWithRoomImpl
import com.mobile.data.repositoryImpl.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository

    @Binds
    abstract fun bindLocalDataSource(impl: UserLocalDataSourceImpl): UserLocalDataSource

    @Binds
    fun bindUsersRepositoryWithRoom(impl: UserRepositoryWithRoomImpl): UserRepositoryWithRoom
}


