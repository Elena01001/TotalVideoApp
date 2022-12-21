package ru.netology.totalvideoapp.data

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface TotalVideoApi {

    @FormUrlEncoded
    @GET("/dev/test_timer?secs=3")
    suspend fun getResultString(
        @Field("login") login: String,
        @Field("password") password: String
    ): Result<String>
}