package com.mohammedoucif.attraxiatest.fetchChatsComponent.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.mohammedoucif.attraxiatest.fetchChatsComponent.data.datasource.remote.model.ChatDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.tasks.await

/*
* I am using two flows to fetch updates from the root chats collection
* and the messages collection group to be able to detect updates when
* a user adds a new chat and also when a user sends a new message
* in any of the available chats*/
class RemoteDataSourceImpl(
    private val fireStore: FirebaseFirestore
) : RemoteDataSource {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun fetchChats(userId: String): Flow<List<ChatDTO>> {

        val messagesSnapshots = fireStore.collectionGroup("messages")
            .snapshots().mapLatest { messagesCollectionGroup ->
                messagesCollectionGroup.documents.groupBy { message ->
                    message.getString("chat_id") ?: ""
                }.filter { it.key.isNotBlank() }
            }

        val chatsSnapshots = fireStore.collection("chats")
            .snapshots().mapLatest { chatCollection ->
                chatCollection.documents
            }

        return combine(messagesSnapshots, chatsSnapshots) { messages, chats ->
            chats.map { chat ->
                if (messages.containsKey(chat.id)) {
                    ChatDTO(
                        id = chat.id,
                        name = fireStore.collection("chats")
                            .document(chat.id).get().await()
                            .getString("name") ?: "chat",
                        notSeenMessagesNumber = messages[chat.id]?.count { message ->
                            message["is_seen"] == false && message["receiver_id"].toString()
                                .equals(userId, ignoreCase = true)
                        } ?: 0
                    )
                } else {
                    ChatDTO(
                        id = chat.id,
                        name = chat.getString("name") ?: "Chat",
                        notSeenMessagesNumber = 0
                    )
                }
            }
        }
    }
}