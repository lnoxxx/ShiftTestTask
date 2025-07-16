package com.top.data.randomUser.randomUserDataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RandomUserEntity::class], version = 1)
@TypeConverters(RandomUserConverters::class)
abstract class RandomUserDatabase: RoomDatabase() {
    abstract fun getRandomUsersDao(): RandomUserDao
}