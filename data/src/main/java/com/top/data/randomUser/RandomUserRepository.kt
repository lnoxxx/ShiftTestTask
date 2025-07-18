package com.top.data.randomUser

import com.top.data.randomUser.models.UpdateUsersResult
import com.top.data.randomUser.models.User
import kotlinx.coroutines.flow.Flow

interface RandomUserRepository {
    val users: Flow<List<User>>
    suspend fun updateUsers(userCount: Int = DEFAULT_USER_COUNT): UpdateUsersResult
    suspend fun getUserById(userId: Int): User?

    companion object {
        const val DEFAULT_USER_COUNT = 30
    }
}