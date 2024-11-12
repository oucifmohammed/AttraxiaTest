package com.mohammedoucif.attraxiatest.addChatComponent.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore

class RemoteDataSourceImpl(
    private val firestore: FirebaseFirestore
): RemoteDataSource {

    override suspend fun addChat(chatName: String) {
        firestore.collection("chats").document().set(mapOf("name" to chatName))
    }
}