package com.mobile.data.di

import com.mobile.data.network.InterviewService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object UsersApiModule {
    @Provides
    fun provideUsersApi(
        retrofit: Retrofit
    ): InterviewService {
        return retrofit.create(InterviewService::class.java)
    }
}