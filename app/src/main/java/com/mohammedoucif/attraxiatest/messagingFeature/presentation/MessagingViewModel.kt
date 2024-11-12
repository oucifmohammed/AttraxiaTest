package com.mohammedoucif.attraxiatest.messagingFeature.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.usecase.FetchMessagesUseCase
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.usecase.Result
import com.mohammedoucif.attraxiatest.messagingFeature.ui.FirstUserMessagingScreenRoute
import com.mohammedoucif.attraxiatest.sendMessageComponent.domain.usecase.SendMessageUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MessagingViewModel(
    saveStateHandle: SavedStateHandle,
    fetchMessagesUseCase: FetchMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
): ViewModel() {

    val chatId = saveStateHandle.toRoute<FirstUserMessagingScreenRoute>().chatId

    @OptIn(ExperimentalCoroutinesApi::class)
    val screenState = fetchMessagesUseCase(chatId).mapLatest { result ->
        when(result) {
            is Result.Success -> {
                ScreenState.SuccessState(result.data.map { it.toMessageUI() })
            }
            Result.NetworkError -> {
                ScreenState.NetworkErrorState
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ScreenState.LoadingState
    )

    fun sendMessage(content: String) {
        viewModelScope.launch {
            sendMessageUseCase(chatId, content)
        }
    }
}