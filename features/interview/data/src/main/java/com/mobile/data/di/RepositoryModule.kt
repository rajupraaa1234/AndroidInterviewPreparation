package com.mobile.data.di

import com.interview.domain.repository.UsersRepository
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

}


