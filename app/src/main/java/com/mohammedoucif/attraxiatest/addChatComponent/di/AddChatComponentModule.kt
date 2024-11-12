package com.mohammedoucif.attraxiatest.addChatComponent.di

import com.mohammedoucif.attraxiatest.addChatComponent.data.datasource.ChatsRepositoryImpl
import com.mohammedoucif.attraxiatest.addChatComponent.data.datasource.remote.RemoteDataSource
import com.mohammedoucif.attraxiatest.addChatComponent.data.datasource.remote.RemoteDataSourceImpl
import com.mohammedoucif.attraxiatest.addChatComponent.domain.repository.ChatsRepository
import com.mohammedoucif.attraxiatest.addChatComponent.domain.usecase.AddChatUseCase
import com.mohammedoucif.attraxiatest.addChatComponent.domain.usecase.AddChatUseCaseImpl
import org.koin.dsl.module

val addChatComponentModule = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    factory<ChatsRepository> {
        ChatsRepositoryImpl(get())
    }
    factory<AddChatUseCase> {
        AddChatUseCaseImpl(get())
    }
}