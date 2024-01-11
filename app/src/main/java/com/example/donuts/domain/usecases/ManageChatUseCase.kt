package com.example.donuts.domain.usecases

import com.example.donuts.domain.entities.Message
import com.example.donuts.domain.repository.IChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IManageChatUseCase {
    suspend fun getMessages(chatId: String): Flow<List<Message>>
    suspend fun sendMessage(message: String, chatId: String, imageUrl: String): Message
}

class ManageChatUseCaseImp @Inject constructor(private val chatRpo: IChatRepository) :
    IManageChatUseCase {
    override suspend fun getMessages(chatId: String): Flow<List<Message>> {
        return chatRpo.getMessages(chatId)
    }

    override suspend fun sendMessage(message: String, chatId: String, imageUrl: String): Message {
        return chatRpo.sendMessage(message, chatId, imageUrl)
    }

}