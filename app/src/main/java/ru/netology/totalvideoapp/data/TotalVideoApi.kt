package ru.netology.totalvideoapp.data

import retrofit2.http.GET

interface TotalVideoApi {

    @GET("/dev/test_timer?secs=3")
    suspend fun getResultString(): Result<String>
}