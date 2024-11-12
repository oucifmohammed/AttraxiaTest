package com.mohammedoucif.attraxiatest.fetchMessagesComponent.data.datasource.remote

import com.mohammedoucif.attraxiatest.fetchMessagesComponent.data.datasource.remote.model.MessageDTO
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun fetchMessages(userId: String, chatId: String): Flow<List<MessageDTO>>
}