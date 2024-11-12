package com.mohammedoucif.attraxiatest.addChatComponent.data.datasource

import com.mohammedoucif.attraxiatest.addChatComponent.data.datasource.remote.RemoteDataSource
import com.mohammedoucif.attraxiatest.addChatComponent.domain.repository.ChatsRepository

class ChatsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): ChatsRepository {
    override suspend fun addChat(chatName: String) {
        remoteDataSource.addChat(chatName)
    }
}