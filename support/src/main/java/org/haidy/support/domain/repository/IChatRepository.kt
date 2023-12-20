package org.haidy.support.domain.repository

import org.haidy.support.domain.entities.Message
import kotlinx.coroutines.flow.Flow

interface IChatRepository {
    suspend fun getAvailableChat(): Flow<String>
    suspend fun getMessages(chatId: String): Flow<List<Message>>
    suspend fun sendMessage(message: String, chatId: String, imageUrl: String): Message
}