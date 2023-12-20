package com.example.donuts.data.dto

data class ChatDto(
    val chatId: String? = null,
    val messages: List<MessageDto?>? = null,
    val closed: Boolean? = null,
    val busy: Boolean? = null
)
