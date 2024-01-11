package org.haidy.support.domain.entities

data class Message(
    val chatId: String,
    val id: String,
    val senderId: String,
    val message: String,
    val isMe: Boolean,
    val imageUrl: String,
)
