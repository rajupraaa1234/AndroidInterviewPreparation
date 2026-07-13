package com.mobile.data.di

import android.content.Context
import androidx.room.Room
import com.mobile.data.local.dao.UserDao
import com.mobile.data.local.db.AppDatabase
import com.mobile.data.network.InterviewService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UsersApiModule {

    @Provides
    fun provideUsersApi(
        retrofit: Retrofit
    ): InterviewService {
        return retrofit.create(InterviewService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "user.db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}