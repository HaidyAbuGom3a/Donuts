package org.haidy.support.data.mapper

import org.haidy.support.data.dto.MessageDto
import org.haidy.support.domain.entities.Message

fun MessageDto.toMessage(userId: String): Message {
    return Message(
        id = id  ?: "",
        message = message  ?: "",
        imageUrl = imageUrl  ?: "",
        isMe = senderId == userId,
        senderId = senderId  ?: "",
        chatId = chatId ?: ""
    )
}