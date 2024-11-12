package com.mohammedoucif.attraxiatest.fetchMessagesComponent.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.data.datasource.remote.model.MessageDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.tasks.await

class RemoteDataSourceImpl(
    private val firestore: FirebaseFirestore
) : RemoteDataSource {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun fetchMessages(
        userId: String,
        chatId: String
    ): Flow<List<MessageDTO>> =
        firestore.collection("chats").document(chatId)
            .collection("messages").snapshots()
            .mapLatest { messagesCollection ->
                messagesCollection.documents.map { message ->

                    if (message.getString("receiver_id") == userId) {
                        firestore.collection("chats")
                            .document(chatId).collection("messages")
                            .document(message.id).update(mapOf("is_seen" to true)).await()
                    }

                    MessageDTO(
                        id = message.getString("id") ?: "",
                        senderId = message.getString("sender_id") ?: "",
                        receiverId = message.getString("receiver_id") ?: "",
                        content = message.getString("content") ?: "",
                        isSeen = message.getBoolean("is_seen") ?: false,
                        timeSent = message.getLong("time_sent") ?: 0L
                    )
                }.sortedByDescending { it.timeSent }
            }
}