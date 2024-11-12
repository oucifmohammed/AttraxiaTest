package com.mohammedoucif.attraxiatest.addChatComponent.data.datasource.remote

interface RemoteDataSource {
    suspend fun addChat(chatName: String)
}