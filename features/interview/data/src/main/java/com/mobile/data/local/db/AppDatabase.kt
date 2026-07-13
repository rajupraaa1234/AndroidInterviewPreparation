package com.mobile.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobile.data.local.dao.UserDao
import com.mobile.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}