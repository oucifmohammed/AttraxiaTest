package com.mohammedoucif.attraxiatest.app

import android.app.Application
import com.mohammedoucif.attraxiatest.addChatComponent.di.addChatComponentModule
import com.mohammedoucif.attraxiatest.fetchChatsComponent.di.fetchChatsComponentModule
import com.mohammedoucif.attraxiatest.chatsFeature.di.chatsFeatureModule
import com.mohammedoucif.attraxiatest.core.di.datasource.remote.remoteDataSourceModule
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.di.fetchMessagesComponentModule
import com.mohammedoucif.attraxiatest.messagingFeature.di.messagingFeatureModule
import com.mohammedoucif.attraxiatest.sendMessageComponent.di.sendMessageComponentModule
import org.koin.core.context.startKoin

class AttraxiaTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                remoteDataSourceModule,
                fetchChatsComponentModule,
                chatsFeatureModule,
                fetchMessagesComponentModule,
                sendMessageComponentModule,
                messagingFeatureModule,
                addChatComponentModule
            )
        }
    }
}