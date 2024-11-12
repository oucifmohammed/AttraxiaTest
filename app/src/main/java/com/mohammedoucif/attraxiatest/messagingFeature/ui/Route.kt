package com.mohammedoucif.attraxiatest.messagingFeature.ui

import kotlinx.serialization.Serializable

@Serializable
data class FirstUserMessagingScreenRoute(
    val chatId: String
)

@Serializable
data class SecondUserMessagingScreenRoute(
    val chatId: String
)