package com.mohammedoucif.attraxiatest.addChatComponent.domain.usecase

import com.mohammedoucif.attraxiatest.addChatComponent.domain.repository.ChatsRepository

class AddChatUseCaseImpl(
    private val repository: ChatsRepository
): AddChatUseCase {

    override suspend fun invoke(chatName: String) {
        repository.addChat(chatName)
    }
}