package ru.netology.totalvideoapp.domain.usecase

import ru.netology.totalvideoapp.domain.Repository

class GetUrlDataUseCase(
    private val repository: Repository
) {
    suspend fun execute(login: String, password: String): Result<String> {
        return repository.getUrlString(login, password)
    }
}