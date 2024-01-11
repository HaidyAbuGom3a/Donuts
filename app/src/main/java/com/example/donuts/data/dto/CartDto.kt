package com.example.donuts.data.dto

data class CartDto(
    val id: String? = null,
    val items: List<CartItemDto>? = null,
    val totalPrice: Double? = null
)
