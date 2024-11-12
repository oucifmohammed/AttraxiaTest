package com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.usecase

import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.repository.ChatsRepository
import kotlinx.coroutines.flow.Flow

class FetchChatsUseCaseImpl(
    private val repository: ChatsRepository
): FetchChatsUseCase {

    override fun invoke(): Flow<Result> = repository.fetchChats()
}