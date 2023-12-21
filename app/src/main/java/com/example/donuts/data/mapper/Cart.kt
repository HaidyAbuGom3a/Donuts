package com.example.donuts.data.mapper

import com.example.donuts.data.dto.CartDto
import com.example.donuts.domain.entities.Cart

fun CartDto.toEntity(): Cart {
    return Cart(
        id = id ?: "",
        items = items?.map { it.toEntity() } ?: emptyList(),
        totalPrice = totalPrice ?: 0.0
    )
}