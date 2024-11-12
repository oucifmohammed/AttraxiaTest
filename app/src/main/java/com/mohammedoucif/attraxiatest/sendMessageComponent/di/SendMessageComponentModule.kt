package com.mohammedoucif.attraxiatest.sendMessageComponent.di

import com.mohammedoucif.attraxiatest.sendMessageComponent.data.datasource.MessagingRepositoryImpl
import com.mohammedoucif.attraxiatest.sendMessageComponent.data.datasource.remote.RemoteDataSource
import com.mohammedoucif.attraxiatest.sendMessageComponent.data.datasource.remote.RemoteDataSourceImpl
import com.mohammedoucif.attraxiatest.sendMessageComponent.domain.repository.MessagingRepository
import com.mohammedoucif.attraxiatest.sendMessageComponent.domain.usecase.SendMessageUseCase
import com.mohammedoucif.attraxiatest.sendMessageComponent.domain.usecase.SendMessageUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

/*
* I am injecting different instances of repositories using two different user ids as we have
* two users (user for each screen)
* */
val sendMessageComponentModule = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    factory<MessagingRepository>(named("User1_Repository")) {
        MessagingRepositoryImpl(get(), "User1", "User2")
    }
    factory<SendMessageUseCase>(named("User1_UseCase")) {
        SendMessageUseCaseImpl(get(named("User1_Repository")))
    }

    factory<MessagingRepository>(named("User2_Repository")) {
        MessagingRepositoryImpl(get(), "User2", "User1")
    }
    factory<SendMessageUseCase>(named("User2_UseCase")) {
        SendMessageUseCaseImpl(get(named("User2_Repository")))
    }
}