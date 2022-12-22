package ru.netology.totalvideoapp.data

import ru.netology.totalvideoapp.domain.Repository

class RepositoryImpl(private val api: TotalVideoApi): Repository {

    override suspend fun getUrlString(): Result<String> {
        return api.getResultString()
    }

}