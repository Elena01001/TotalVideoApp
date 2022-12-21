package ru.netology.totalvideoapp.domain.usecase

import ru.netology.totalvideoapp.domain.Repository

class IncrementUseCase(
    private val repository: Repository
) {
    suspend fun execute(number: Int): Int {
        return repository.increment(number)
    }
}