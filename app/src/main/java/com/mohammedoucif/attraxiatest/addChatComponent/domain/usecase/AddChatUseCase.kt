package com.mohammedoucif.attraxiatest.addChatComponent.domain.usecase

interface AddChatUseCase {
    suspend operator fun invoke(chatName: String)
}