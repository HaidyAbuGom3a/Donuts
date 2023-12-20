package com.example.donuts.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.donuts.data.dto.ChatDto
import com.example.donuts.data.dto.MessageDto
import com.example.donuts.data.mapper.toMessage
import com.example.donuts.domain.entities.Message
import com.example.donuts.domain.repository.IChatRepository
import com.example.donuts.domain.repository.IUserRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.time.Instant
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val userRepo: IUserRepository
) : IChatRepository {
    override suspend fun getMessages(chatId: String): Flow<List<Message>> {
        val userId = userRepo.getUserId()
        return callbackFlow {
            val messagesCollectionRef =
                firestore.collection(CHATS).document(chatId)
            val listenerRegistration = messagesCollectionRef
                .addSnapshotListener { querySnapshot, _ ->
                    val messages = querySnapshot?.toObject(ChatDto::class.java)?.messages
                        ?.mapNotNull { it?.toMessage(userId) } ?: emptyList()
                    trySend(messages).isSuccess
                }

            awaitClose { listenerRegistration.remove() }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun sendMessage(
        message: String,
        chatId: String,
        imageUrl: String
    ): Message {
        val id = userRepo.getUserId()
        val chatsCollectionRef = firestore.collection(CHATS)
        return if (chatId.isEmpty()) {
            createChat(chatsCollectionRef, id, message, imageUrl)
        } else {
            val existingChatDocumentRef = chatsCollectionRef.document(chatId)
            val existingChatDto =
                existingChatDocumentRef.get().await().toObject(ChatDto::class.java)

            val messageDto = createMessage(id, message, imageUrl, chatId)
            existingChatDto?.let {
                val messages = it.messages?.toMutableList() ?: mutableListOf()
                messages.add(messageDto)
                existingChatDocumentRef.set(it.copy(messages = messages)).await()
            }
            messageDto.toMessage(id)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun createChat(
        chatsCollectionRef: CollectionReference,
        senderId: String,
        message: String,
        imageUrl: String
    ): Message {
        val chatId = chatsCollectionRef.document().id
        val messageDto = createMessage(senderId, message, imageUrl, chatId)
        chatsCollectionRef.document(chatId).set(
            ChatDto(
                chatId = chatId,
                messages = listOf(
                    messageDto
                ),
                busy = false,
                closed = false,
            )
        ).await()
        return messageDto.toMessage(senderId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createMessage(
        id: String,
        message: String,
        imageUrl: String,
        chatId: String
    ): MessageDto {
        return MessageDto(
            id = Instant.now().toEpochMilli().toString(),
            senderId = id,
            message = message,
            imageUrl = imageUrl,
            chatId = chatId
        )
    }


    companion object {
        const val CHATS = "chats"
    }
}