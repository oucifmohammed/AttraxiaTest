package com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.entity

data class Message(
    val id: String,
    val currentUserId: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val isSeen: Boolean,
    val timeSent: Long
)
