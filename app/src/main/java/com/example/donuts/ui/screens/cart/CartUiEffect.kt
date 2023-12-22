package com.example.donuts.ui.screens.cart

sealed class CartUiEffect {
    data object NavigateUp : CartUiEffect()
    data class ShowToastMessage(val message: String) : CartUiEffect()
}