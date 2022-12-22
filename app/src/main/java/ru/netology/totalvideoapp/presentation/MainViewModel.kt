package ru.netology.totalvideoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.netology.totalvideoapp.domain.usecase.GetUrlDataUseCase
import ru.netology.totalvideoapp.domain.usecase.IncrementUseCase

class MainViewModel(
    private val incrementUseCase: IncrementUseCase,
    private val getUrlDataUseCase: GetUrlDataUseCase
) : ViewModel() {

    private var count = 0
    private val isActive = true

    private var incrementEvent = MutableStateFlow<Int>(count)
    val incrementFlow: Flow<Int> = incrementEvent

    private var getSuccessUrlEvent = MutableStateFlow<String?>(null)
    val successUrlDataFlow: Flow<String?> = getSuccessUrlEvent

    private var singleShotEvent = MutableSharedFlow<String>()
    val singleShotFlow: Flow<String> = singleShotEvent

    private var progressBarEvent = MutableStateFlow<Boolean>(false)
    val progressBarFlow: Flow<Boolean> = progressBarEvent

    fun onIncrementButtonClicked() {
        viewModelScope.launch {
            count = incrementUseCase.execute(count)
            incrementEvent.emit(count)
        }
    }

    fun onUrlButtonClicked() {
        viewModelScope.launch {
            if (isActive) {
                progressBarEvent.emit(true)
                getUrlDataUseCase.execute().onSuccess {
                    getSuccessUrlEvent.emit(it)
                    progressBarEvent.emit(false)
                }
                    .onFailure {
                        singleShotEvent.emit(it.localizedMessage?.toString() ?: "")
                        getSuccessUrlEvent.emit(null)
                        progressBarEvent.emit(false)
                    }
            }
        }
    }
}