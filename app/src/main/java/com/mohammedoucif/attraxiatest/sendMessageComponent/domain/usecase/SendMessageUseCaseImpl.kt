package com.mohammedoucif.attraxiatest.sendMessageComponent.domain.usecase

import com.mohammedoucif.attraxiatest.sendMessageComponent.domain.repository.MessagingRepository

class SendMessageUseCaseImpl(
    private val repository: MessagingRepository
): SendMessageUseCase {
    override suspend fun invoke(chatId: String, content: String) {
        repository.sendMessage(chatId, content)
    }
}