package org.haidy.support.data.dto

data class ChatDto(
    val chatId: String? = null,
    val messages: List<MessageDto?>? = null,
    val closed: Boolean? = null,
    val busy: Boolean? = null
)
