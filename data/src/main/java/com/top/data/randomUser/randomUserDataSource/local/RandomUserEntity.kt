package com.top.data.randomUser.randomUserDataSource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.top.data.randomUser.randomUserDataSource.remote.UserNetwork

@Entity(tableName = "users")
data class RandomUserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val user: UserNetwork,
)