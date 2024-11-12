package com.mohammedoucif.attraxiatest.chatsFeature.presentation

import com.mohammedoucif.attraxiatest.designSystem.component.chatItem.model.ChatUIModel

sealed class ScreenState {
    data object LoadingState: ScreenState()

    data class SuccessState(
        val chats: List<ChatUIModel>
    ): ScreenState()

    data object NetworkErrorState: ScreenState()
}
