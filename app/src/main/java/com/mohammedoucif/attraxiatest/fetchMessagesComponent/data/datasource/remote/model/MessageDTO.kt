package com.mohammedoucif.attraxiatest.fetchMessagesComponent.data.datasource.remote.model

data class MessageDTO(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val isSeen: Boolean,
    val timeSent: Long
)
