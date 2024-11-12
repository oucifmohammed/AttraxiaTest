package com.mohammedoucif.attraxiatest.sendMessageComponent.data.datasource

import com.mohammedoucif.attraxiatest.sendMessageComponent.data.datasource.remote.RemoteDataSource
import com.mohammedoucif.attraxiatest.sendMessageComponent.domain.repository.MessagingRepository

class MessagingRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val senderId: String,
    private val receiverId: String
): MessagingRepository {

    override suspend fun sendMessage(chatId: String, content: String) {
        remoteDataSource.sendMessage(
            chatId = chatId,
            senderId = senderId,
            receiverId = receiverId,
            content = content
        )
    }
}