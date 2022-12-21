package ru.netology.totalvideoapp.data

import ru.netology.totalvideoapp.domain.Repository

class RepositoryImpl(private val api: TotalVideoApi): Repository {

    var count = 0

    override suspend fun increment(number: Int): Int {
        return number.inc()
    }

    override suspend fun getUrlString(login: String, password: String): Result<String> {
        return api.getResultString(login, password)
    }

}