package org.haidy.support.data.dto

data class MessageDto(
    val chatId: String? = null,
    val id: String? = null,
    val senderId: String? = null,
    val message: String ?= null,
    val imageUrl: String? = null,
)
