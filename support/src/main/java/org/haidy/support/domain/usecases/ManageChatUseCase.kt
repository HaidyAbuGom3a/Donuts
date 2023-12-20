package org.haidy.support.domain.usecases

import org.haidy.support.domain.entities.Message
import org.haidy.support.domain.repository.IChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IManageChatUseCase {
    suspend fun getMessages(chatId: String): Flow<List<Message>>
    suspend fun sendMessage(message: String, chatId: String, imageUrl: String): Message
    suspend fun getAvailableChat(): Flow<String>
}

class ManageChatUseCaseImp @Inject constructor(private val chatRpo: IChatRepository) :
    IManageChatUseCase {
    override suspend fun getMessages(chatId: String): Flow<List<Message>> {
        return chatRpo.getMessages(chatId)
    }

    override suspend fun sendMessage(message: String, chatId: String, imageUrl: String): Message {
        return chatRpo.sendMessage(message, chatId, imageUrl)
    }

    override suspend fun getAvailableChat(): Flow<String> {
        return chatRpo.getAvailableChat()
    }

}