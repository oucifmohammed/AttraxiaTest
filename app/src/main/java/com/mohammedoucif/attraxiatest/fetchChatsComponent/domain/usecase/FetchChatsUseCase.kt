package com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.usecase

import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.entity.Chat
import kotlinx.coroutines.flow.Flow

interface FetchChatsUseCase {
    operator fun invoke(): Flow<Result>
}

sealed interface Result {
    data class Success(val data: List<Chat>) : Result
    data object NetworkError: Result
}