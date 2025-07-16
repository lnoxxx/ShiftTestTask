package com.top.data.randomUser.randomUserDataSource.local

import androidx.room.TypeConverter
import com.top.data.randomUser.randomUserDataSource.remote.UserNetwork
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RandomUserConverters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromUserNetwork(user: UserNetwork): String {
        return json.encodeToString(user)
    }

    @TypeConverter
    fun toUserNetwork(user: String): UserNetwork {
        return json.decodeFromString(user)
    }
}