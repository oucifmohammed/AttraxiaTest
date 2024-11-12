package com.mohammedoucif.attraxiatest.designSystem.component.messageItem.model

data class MessageUIModel(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val isSeen: Boolean,
    val messageSide: MessageSide,
    val timeSent: String
)

enum class MessageSide {
    START,
    END
}
