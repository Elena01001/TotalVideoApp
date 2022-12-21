package ru.netology.totalvideoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.netology.totalvideoapp.domain.usecase.GetUrlDataUseCase
import ru.netology.totalvideoapp.domain.usecase.IncrementUseCase

class MainViewModel(
    private val incrementUseCase: IncrementUseCase,
    private val getUrlDataUseCase: GetUrlDataUseCase
) : ViewModel() {

    private var incrementEvent = MutableSharedFlow<Int>(1, 1)
    val incrementFlow: Flow<Int> = incrementEvent

    private var getUrlEvent = MutableSharedFlow<String>(1, 1)
    val urlDataFlow: Flow<String> = getUrlEvent

    fun onIncrementButtonClicked(number: Int) {
        viewModelScope.launch {
            incrementUseCase.execute(number)
            incrementEvent.emit(number)
        }
    }

    fun onUrlButtonClicked(login: String, password: String) {
        viewModelScope.launch {
            getUrlDataUseCase.execute(login, password).onSuccess {
                getUrlEvent.emit(it)
            }

        }
    }
}

sealed class ResultState {
    object EMPTY : ResultState()
    data class FAILURE(val message: String) : ResultState()
}