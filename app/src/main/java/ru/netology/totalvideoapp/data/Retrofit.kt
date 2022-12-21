package ru.netology.totalvideoapp.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.netology.totalvideoapp.data.Constants.Companion.BASE_URL
import ru.netology.totalvideoapp.data.exception.ResultCallAdapterFactory

object Retrofit {

    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor("stest", "!Te5t"))
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
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