package org.haidy.support.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import org.haidy.support.domain.entities.Message
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.haidy.support.data.dto.ChatDto
import org.haidy.support.data.dto.MessageDto
import org.haidy.support.data.mapper.toMessage
import org.haidy.support.domain.repository.IChatRepository
import org.haidy.support.domain.repository.IUserRepository
import java.time.Instant
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val userRepo: IUserRepository
) : IChatRepository {

    override suspend fun getAvailableChat(): Flow<String> {
        return callbackFlow {
            val chatsCollectionRef = firestore.collection(CHATS)
            val listenerRegistration = chatsCollectionRef.addSnapshotListener { querySnapshot, _ ->
                if (querySnapshot?.size()!! > 0) {
                    val filteredChats = querySnapshot.map { it.toObject(ChatDto::class.java) }
                        .filter { !it.closed!! && !it.busy!! }
                    if (filteredChats.isNotEmpty()) {
                        val availableChatId = filteredChats.first().chatId
                        if (availableChatId != null) {
                            runBlocking {
                                val chatDto = getChat(availableChatId.toString())
                                chatsCollectionRef.document(availableChatId.toString()).set(chatDto)
                            }
                            trySend(availableChatId).isSuccess
                            close()
                        }
                    }
                }
            }
            awaitClose { listenerRegistration.remove() }
        }
    }

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
        val existingChatDocumentRef = chatsCollectionRef.document(chatId)
        val existingChatDto =
            existingChatDocumentRef.get().await().toObject(ChatDto::class.java)

        val messageDto = createMessage(id, message, imageUrl, chatId)
        existingChatDto?.let {
            val messages = it.messages?.toMutableList() ?: mutableListOf()
            messages.add(messageDto)
            existingChatDocumentRef.set(it.copy(messages = messages)).await()
        }
        return messageDto.toMessage(id)
    }

    override suspend fun closeChat(chatId: String) {
        val chatDocumentRef = firestore.collection(CHATS).document(chatId)
        val chatDto = chatDocumentRef.get().await().toObject<ChatDto>()
        val updatedChat = chatDto?.copy(closed = true)
        if (updatedChat != null) {
            chatDocumentRef.set(updatedChat).await()
        }
    }

    private suspend fun getChat(chatId: String): ChatDto {
        val chatsCollectionRef = firestore.collection(CHATS)
        val existingChatDocumentRef = chatsCollectionRef.document(chatId)
        return existingChatDocumentRef.get().await().toObject(ChatDto::class.java)
            ?.copy(busy = true)!!
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