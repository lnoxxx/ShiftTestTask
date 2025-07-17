package com.top.data.randomUser

import android.util.Log
import com.top.data.randomUser.models.UpdateUsersResult
import com.top.data.randomUser.models.User
import com.top.data.randomUser.models.toUser
import com.top.data.randomUser.randomUserDataSource.local.RandomUserDao
import com.top.data.randomUser.randomUserDataSource.local.RandomUserEntity
import com.top.data.randomUser.randomUserDataSource.remote.RandomUserApi
import com.top.data.randomUser.randomUserDataSource.remote.UserNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okio.IOException

class RandomUserRepositoryImpl(
    private val randomUserApi: RandomUserApi,
    private val randomUserDao: RandomUserDao,
) : RandomUserRepository {

    override val users: Flow<List<User>> =
        randomUserDao.getAllUsers().map { it.map { userEntity -> userEntity.toUser() } }

    override suspend fun updateUsers(userCount: Int): UpdateUsersResult {
        return try {
            val response = randomUserApi.getRandomUsers(userCount)
            if (response.isSuccessful) {
                response.body()?.let {
                    saveToDatabase(it.results)
                    UpdateUsersResult.SUCCESS
                } ?: UpdateUsersResult.SERVER_ERROR
            } else {
                UpdateUsersResult.SERVER_ERROR
            }
        } catch (e: IOException) {
            Log.e("RandomUserRepository (IO)", e.toString())
            UpdateUsersResult.NETWORK_ERROR
        } catch (e: Exception) {
            Log.e("RandomUserRepository (Unknown)", e.toString())
            UpdateUsersResult.UNKNOWN_ERROR
        }
    }

    override suspend fun getUserById(userId: Int): User {
        return withContext(Dispatchers.IO) {
            randomUserDao.getUserById(userId).toUser()
        }
    }

    private suspend fun saveToDatabase(data: List<UserNetwork>) {
        val users = data.map { RandomUserEntity(user = it) }
        randomUserDao.insertAndClearUsers(users)
    }
}