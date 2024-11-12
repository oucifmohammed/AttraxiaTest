package com.mohammedoucif.attraxiatest.fetchChatsComponent.di

import com.mohammedoucif.attraxiatest.fetchChatsComponent.data.ChatsRepositoryImpl
import com.mohammedoucif.attraxiatest.fetchChatsComponent.data.datasource.remote.RemoteDataSource
import com.mohammedoucif.attraxiatest.fetchChatsComponent.data.datasource.remote.RemoteDataSourceImpl
import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.repository.ChatsRepository
import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.usecase.FetchChatsUseCase
import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.usecase.FetchChatsUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

/*
* I am injecting different instances of repositories using two different user ids as we have
* two users (user for each screen)
* */
val fetchChatsComponentModule = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    factory<ChatsRepository>(named("User1_Repository")) {
        ChatsRepositoryImpl(get(), "User1")
    }
    factory<FetchChatsUseCase>(named("User1_UseCase")) {
        FetchChatsUseCaseImpl(get(named("User1_Repository")))
    }

    factory<ChatsRepository>(named("User2_Repository")) {
        ChatsRepositoryImpl(get(), "User2")
    }
    factory<FetchChatsUseCase>(named("User2_UseCase")) {
        FetchChatsUseCaseImpl(get(named("User2_Repository")))
    }
}