package com.mohammedoucif.attraxiatest.sendMessageComponent.domain.repository

interface MessagingRepository {
    suspend fun sendMessage(chatId: String, content: String)
}