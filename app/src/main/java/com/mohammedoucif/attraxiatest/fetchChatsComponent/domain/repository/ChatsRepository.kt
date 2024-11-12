package com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.repository

import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.usecase.Result
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {
    fun fetchChats(): Flow<Result>
}