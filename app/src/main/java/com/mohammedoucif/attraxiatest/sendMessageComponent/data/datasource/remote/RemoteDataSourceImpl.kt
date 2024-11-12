package com.mohammedoucif.attraxiatest.sendMessageComponent.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class RemoteDataSourceImpl(
    private val firestore: FirebaseFirestore
): RemoteDataSource {

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun sendMessage(
        chatId: String,
        senderId: String,
        receiverId: String,
        content: String
    ) {

        val message = mapOf(
            "id" to Uuid.random().toString(),
            "chat_id" to chatId,
            "content" to content,
            "sender_id" to senderId,
            "receiver_id" to receiverId,
            "is_seen" to false,
            "time_sent" to System.currentTimeMillis()
        )

        firestore.collection("chats").document(chatId)
            .collection("messages").document().set(message).await()
    }
}