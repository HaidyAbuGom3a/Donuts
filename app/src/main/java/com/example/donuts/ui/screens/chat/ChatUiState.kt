package com.example.donuts.ui.screens.chat

import com.example.donuts.domain.entities.Message

data class ChatUIState(
    val chatId: String = "",
    val messages: List<MessageUIState> = emptyList(),
    val message: String = "",
    val imageUrl: String = ""
)

data class MessageUIState(
    val id: String,
    val senderId: String,
    val message: String,
    val isMe: Boolean,
    val imageUrl: String,
)

fun Message.toUIState(): MessageUIState {
    return MessageUIState(
        id = id,
        message = message,
        isMe = isMe,
        senderId = senderId,
        imageUrl = imageUrl
    )
}