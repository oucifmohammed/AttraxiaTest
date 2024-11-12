package com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.usecase

import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.repository.MessagesRepository
import kotlinx.coroutines.flow.Flow

class FetchMessagesUseCaseImpl(
    private val repository: MessagesRepository
) : FetchMessagesUseCase {

    override fun invoke(chatId: String): Flow<Result> = repository.fetchMessages(chatId)
}