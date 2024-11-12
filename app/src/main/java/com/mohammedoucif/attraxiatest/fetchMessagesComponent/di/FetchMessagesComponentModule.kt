package com.mohammedoucif.attraxiatest.fetchMessagesComponent.di

import com.mohammedoucif.attraxiatest.fetchMessagesComponent.data.MessagesRepositoryImpl
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.data.datasource.remote.RemoteDataSource
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.data.datasource.remote.RemoteDataSourceImpl
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.repository.MessagesRepository
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.usecase.FetchMessagesUseCase
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.usecase.FetchMessagesUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

/*
* I am injecting different instances of repositories using two different user ids as we have
* two users (user for each screen)
* */
val fetchMessagesComponentModule = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    factory<MessagesRepository>(named("User1_Repository")) {
        MessagesRepositoryImpl(get(), "User1")
    }
    factory<FetchMessagesUseCase>(named("User1_UseCase")) {
        FetchMessagesUseCaseImpl(get(named("User1_Repository")))
    }

    factory<MessagesRepository>(named("User2_Repository")) {
        MessagesRepositoryImpl(get(), "User2")
    }
    factory<FetchMessagesUseCase>(named("User2_UseCase")) {
        FetchMessagesUseCaseImpl(get(named("User2_Repository")))
    }
}