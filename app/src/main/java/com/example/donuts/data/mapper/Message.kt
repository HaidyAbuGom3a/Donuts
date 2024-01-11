package com.example.donuts.data.mapper

import com.example.donuts.data.dto.MessageDto
import com.example.donuts.domain.entities.Message

fun MessageDto.toMessage(userId: String): Message{
    return Message(
        id = id  ?: "",
        message = message  ?: "",
        imageUrl = imageUrl  ?: "",
        isMe = senderId == userId,
        senderId = senderId  ?: "",
        chatId = chatId ?: ""
    )
}