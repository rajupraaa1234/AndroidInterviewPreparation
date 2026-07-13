package com.mobile.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getUser(): List<UserEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<UserEntity>)

    @Query("DELETE FROM users")
    suspend fun clear()
}