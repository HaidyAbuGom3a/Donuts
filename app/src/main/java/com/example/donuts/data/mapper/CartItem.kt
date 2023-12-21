package com.example.donuts.data.mapper

import com.example.donuts.data.dto.CartItemDto
import com.example.donuts.domain.entities.CartItem

fun CartItemDto.toEntity(): CartItem {
    return CartItem(
        id = itemId ?: "",
        name = name ?: "",
        amount = amount ?: 0,
        price = price ?: 0.0,
        imageUrl = imageUrl ?: ""
    )
}