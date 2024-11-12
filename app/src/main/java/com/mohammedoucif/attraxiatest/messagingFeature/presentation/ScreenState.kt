package com.mohammedoucif.attraxiatest.messagingFeature.presentation

import com.mohammedoucif.attraxiatest.designSystem.component.messageItem.model.MessageUIModel

sealed class ScreenState {
    data object LoadingState: ScreenState()

    data class SuccessState(
        val messages: List<MessageUIModel>
    ): ScreenState() {
        val isMessagesListEmpty = messages.isEmpty()
    }

    data object NetworkErrorState: ScreenState()
}