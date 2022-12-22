package ru.netology.totalvideoapp.domain

interface Repository {

    suspend fun getUrlString(): Result<String>
}