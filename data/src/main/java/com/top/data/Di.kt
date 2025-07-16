package com.top.data

import android.content.Context
import androidx.room.Room
import com.top.data.randomUser.RandomUserRepository
import com.top.data.randomUser.RandomUserRepositoryImpl
import com.top.data.randomUser.randomUserDataSource.local.RandomUserDao
import com.top.data.randomUser.randomUserDataSource.local.RandomUserDatabase
import com.top.data.randomUser.randomUserDataSource.remote.RandomUserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit =
            Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RANDOM_USER_BASE_URL)
                .build()

        return retrofit
    }

    @Provides
    @Singleton
    fun provideRandomUserApi(retrofit: Retrofit): RandomUserApi {
        return retrofit.create(RandomUserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRandomUserDataBase(@ApplicationContext context: Context): RandomUserDatabase {
        return Room.databaseBuilder(
            context,
            RandomUserDatabase::class.java,
            "random_users_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRandomUserDao(randomUserDatabase: RandomUserDatabase): RandomUserDao {
        return randomUserDatabase.getRandomUsersDao()
    }

    @Provides
    @Singleton
    fun provideRandomUserRepository(
        randomUserApi: RandomUserApi,
        randomUserDao: RandomUserDao
    ): RandomUserRepository {
        return RandomUserRepositoryImpl(randomUserApi, randomUserDao)
    }
}