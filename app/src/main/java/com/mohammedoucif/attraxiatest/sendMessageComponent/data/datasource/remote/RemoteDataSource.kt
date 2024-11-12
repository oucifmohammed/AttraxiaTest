package com.mohammedoucif.attraxiatest.sendMessageComponent.data.datasource.remote

interface RemoteDataSource {
    suspend fun sendMessage(chatId: String, senderId: String, receiverId: String, content: String)
}