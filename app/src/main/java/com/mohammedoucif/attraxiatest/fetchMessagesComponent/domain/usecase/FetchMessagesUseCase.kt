package com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.usecase

import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.entity.Message
import kotlinx.coroutines.flow.Flow

interface FetchMessagesUseCase {
    operator fun invoke(chatId: String): Flow<Result>
}

sealed interface Result {
    data class Success(val data: List<Message>) : Result
    data object NetworkError: Result
}