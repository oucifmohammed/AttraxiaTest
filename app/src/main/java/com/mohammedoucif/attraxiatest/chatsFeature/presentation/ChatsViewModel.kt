package com.mohammedoucif.attraxiatest.chatsFeature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammedoucif.attraxiatest.addChatComponent.domain.usecase.AddChatUseCase
import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.usecase.FetchChatsUseCase
import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.usecase.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatsViewModel(
    fetchChatsUseCase: FetchChatsUseCase,
    private val addChatUseCase: AddChatUseCase
): ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val screenState = fetchChatsUseCase().mapLatest { result ->
        when(result) {
            is Result.Success -> {
                ScreenState.SuccessState(result.data.map { it.toChatUI() })
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

    fun addChat(chatName: String) {
        viewModelScope.launch {
            addChatUseCase(chatName)
        }
    }
}