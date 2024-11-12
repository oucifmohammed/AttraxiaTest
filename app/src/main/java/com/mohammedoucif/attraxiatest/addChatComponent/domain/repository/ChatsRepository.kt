package com.mohammedoucif.attraxiatest.addChatComponent.domain.repository

interface ChatsRepository {
    suspend fun addChat(chatName: String)
}