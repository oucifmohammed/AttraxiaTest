package com.mohammedoucif.attraxiatest.chatsFeature.di

import com.mohammedoucif.attraxiatest.chatsFeature.presentation.ChatsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/*
* I am injecting two instances of use-cases as we have only two users (one user for each screen)
* each use-case instance has a different repository instance with different user id
* */
val chatsFeatureModule = module {
    viewModel(named("User1_ViewModel")) {
        ChatsViewModel(get(named("User1_UseCase")), get())
    }

    viewModel(named("User2_ViewModel")) {
        ChatsViewModel(get(named("User2_UseCase")), get())
    }
}