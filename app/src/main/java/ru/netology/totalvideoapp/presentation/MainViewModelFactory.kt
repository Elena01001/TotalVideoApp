package ru.netology.totalvideoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.netology.totalvideoapp.data.RepositoryImpl
import ru.netology.totalvideoapp.data.Retrofit
import ru.netology.totalvideoapp.domain.usecase.GetUrlDataUseCase
import ru.netology.totalvideoapp.domain.usecase.IncrementUseCase

class MainViewModelFactory : ViewModelProvider.Factory {

    private val totalVideoApi by lazy(LazyThreadSafetyMode.NONE) {
        Retrofit.api
    }

    private val repository by lazy(LazyThreadSafetyMode.NONE) {
        RepositoryImpl(totalVideoApi)
    }

    private val incrementUseCase by lazy(LazyThreadSafetyMode.NONE) {
        IncrementUseCase(repository)
    }

    private val getUrlDataUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUrlDataUseCase(repository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(incrementUseCase, getUrlDataUseCase) as T
    }

}