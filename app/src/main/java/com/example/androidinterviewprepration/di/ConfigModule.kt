package com.example.androidinterviewprepration.di

import com.example.androidinterviewprepration.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}