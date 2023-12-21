package com.example.donuts.domain.entities

data class Cart(
    val id: String,
    val items: List<CartItem>,
    val totalPrice:Double
)
