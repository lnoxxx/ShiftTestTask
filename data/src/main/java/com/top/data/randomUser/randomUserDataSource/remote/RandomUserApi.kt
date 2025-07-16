package com.top.data.randomUser.randomUserDataSource.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("api/")
    suspend fun getRandomUsers(@Query("results") count: Int): Response<RandomUserResponse>
}