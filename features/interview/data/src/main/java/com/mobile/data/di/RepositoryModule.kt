package com.mobile.data.di

import com.interview.domain.repository.UsersRepository
import com.mobile.data.network.InterviewService
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
abstract class RepositoryModule {
    @Binds
    abstract fun bindUsersRepository(
        impl: UsersRepositoryImpl
    ): UsersRepository
}