package ru.netology.totalvideoapp.domain.usecase

class IncrementUseCase {

    fun execute(number: Int): Int {
        return number.inc()
    }
}