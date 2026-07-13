package com.mobile.core_cache.di

import com.mobile.core_cache.cache.MemoryCache
import com.mobile.core_cache.cache.MemoryCacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun provideMemoryCache(impl: MemoryCacheImpl) : MemoryCache
}


