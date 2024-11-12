package com.mohammedoucif.attraxiatest.designSystem.component.chatItem.model

import androidx.compose.runtime.Stable

@Stable
data class ChatUIModel(
    val id: String,
    val name: String,
    val newMessagesNumber: Int
) {
    val areAllMessagesSeen: Boolean
        get() = newMessagesNumber > 0
}
