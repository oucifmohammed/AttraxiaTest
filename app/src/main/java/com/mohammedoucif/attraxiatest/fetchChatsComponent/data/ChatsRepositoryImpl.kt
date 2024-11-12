package com.mohammedoucif.attraxiatest.fetchChatsComponent.data

import com.mohammedoucif.attraxiatest.fetchChatsComponent.data.datasource.remote.RemoteDataSource
import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.entity.Chat
import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.repository.ChatsRepository
import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.usecase.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class ChatsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val userId: String
) : ChatsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun fetchChats(): Flow<Result> =
        remoteDataSource.fetchChats(userId).mapLatest { chats ->
            Result.Success(chats.map {
                Chat(
                    id = it.id,
                    name = it.name,
                    newMessagesNumber = it.notSeenMessagesNumber
                )
            })
        }
}