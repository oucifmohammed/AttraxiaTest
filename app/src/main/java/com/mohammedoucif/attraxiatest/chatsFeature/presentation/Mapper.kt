package com.mohammedoucif.attraxiatest.chatsFeature.presentation

import com.mohammedoucif.attraxiatest.designSystem.component.chatItem.model.ChatUIModel
import com.mohammedoucif.attraxiatest.fetchChatsComponent.domain.entity.Chat

fun Chat.toChatUI(): ChatUIModel = ChatUIModel(
    id = id,
    name = name,
    newMessagesNumber = newMessagesNumber
)