package com.mohammedoucif.attraxiatest.fetchMessagesComponent.data

import com.mohammedoucif.attraxiatest.fetchMessagesComponent.data.datasource.remote.RemoteDataSource
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.entity.Message
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.repository.MessagesRepository
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.usecase.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class MessagesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val userId: String
) : MessagesRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun fetchMessages(chatId: String): Flow<Result> = remoteDataSource.fetchMessages(
        userId,
        chatId
    ).mapLatest { messages ->
        Result.Success(messages.filter { it.id != "" }.map { message ->
            Message(
                id = message.id,
                currentUserId = userId,
                senderId = message.senderId,
                receiverId = message.receiverId,
                content = message.content,
                isSeen = message.isSeen &&
                        !message.receiverId.equals(userId, ignoreCase = true),
                timeSent = message.timeSent
            )
        })
    }
}