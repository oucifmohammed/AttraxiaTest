package com.mohammedoucif.attraxiatest.sendMessageComponent.domain.usecase

interface SendMessageUseCase {
    suspend operator fun invoke(chatId: String, content: String)
}