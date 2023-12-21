package com.example.donuts.domain.entities

data class CartItem(
    val id: String,
    val name: String,
    val price: Double,
    val amount: Int,
    val imageUrl: String
)
