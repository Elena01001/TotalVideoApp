package ru.netology.totalvideoapp.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.netology.totalvideoapp.data.TotalVideoApi
import ru.netology.totalvideoapp.data.exception.ResultCallAdapterFactory
import ru.netology.totalvideoapp.data.retrofit.Constants.Companion.BASE_URL

object Retrofit {

    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor("stest", "!Te5t"))
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    val api: TotalVideoApi by lazy {
        retrofit.create(TotalVideoApi::class.java)
    }
}

class Constants {
    companion object {
        const val BASE_URL = "https://dev.totalvideo.ru"
    }
}