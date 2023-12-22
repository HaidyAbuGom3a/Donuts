package com.example.donuts.ui.screens.cart

import com.example.donuts.domain.entities.Cart
import com.example.donuts.domain.entities.CartItem

data class CartUiState(
    val isLoading: Boolean = false,
    val items: List<CartItemUiState> = emptyList(),
    val totalPrice: Double = 0.0
)

data class CartItemUiState(
    val name: String = "",
    val imageUrl: String = "",
    val amount: Int = 0,
)

fun CartItem.toUiState(): CartItemUiState {
    return CartItemUiState(
        name = name,
        imageUrl = imageUrl,
        amount = amount
    )
}

fun Cart.toUiState(): CartUiState {
    return CartUiState(
        items = items.map { it.toUiState() },
        totalPrice = totalPrice
    )
}