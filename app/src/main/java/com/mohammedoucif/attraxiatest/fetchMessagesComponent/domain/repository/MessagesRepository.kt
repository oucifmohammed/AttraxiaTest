package com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.repository

import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.usecase.Result
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
    fun fetchMessages(chatId: String): Flow<Result>
}