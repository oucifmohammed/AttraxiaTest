package com.mohammedoucif.attraxiatest.messagingFeature.di

import com.mohammedoucif.attraxiatest.messagingFeature.presentation.MessagingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
/*
* I am injecting two instances of use-cases as we have only two users (one user for each screen)
* each use-case instance has a different repository instance with different user id
* */
val messagingFeatureModule = module {
    viewModel(named("User1_ViewModel")) {
        MessagingViewModel(
            get(),
            get(named("User1_UseCase")),
            get(named("User1_UseCase"))
        )
    }

    viewModel(named("User2_ViewModel")) {
        MessagingViewModel(
            get(),
            get(named("User2_UseCase")),
            get(named("User2_UseCase"))
        )
    }
}