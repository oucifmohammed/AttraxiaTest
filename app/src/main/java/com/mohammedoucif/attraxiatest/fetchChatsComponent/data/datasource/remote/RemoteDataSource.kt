package com.mohammedoucif.attraxiatest.fetchChatsComponent.data.datasource.remote

import com.mohammedoucif.attraxiatest.fetchChatsComponent.data.datasource.remote.model.ChatDTO
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun fetchChats(userId: String): Flow<List<ChatDTO>>
}