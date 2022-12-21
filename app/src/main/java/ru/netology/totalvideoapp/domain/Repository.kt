package ru.netology.totalvideoapp.domain

interface Repository {

    suspend fun increment (number:Int): Int
    suspend fun getUrlString(login: String, password: String): Result<String>
}