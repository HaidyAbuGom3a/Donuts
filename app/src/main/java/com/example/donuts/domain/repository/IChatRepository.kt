package com.example.donuts.domain.repository

import com.example.donuts.domain.entities.Message
import kotlinx.coroutines.flow.Flow

interface IChatRepository {
    suspend fun getMessages(chatId: String): Flow<List<Message>>
    suspend fun sendMessage(message: String, chatId: String, imageUrl: String): Message
}