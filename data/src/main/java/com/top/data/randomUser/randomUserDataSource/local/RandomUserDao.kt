package com.top.data.randomUser.randomUserDataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomUserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<RandomUserEntity>>

    @Insert
    suspend fun insertAllUsers(users: List<RandomUserEntity>)

    @Query("DELETE FROM users")
    suspend fun clearAllUsers()

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): RandomUserEntity

    @Transaction
    suspend fun insertAndClearUsers(users: List<RandomUserEntity>) {
        clearAllUsers()
        insertAllUsers(users)
    }
}