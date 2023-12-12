package com.example.donuts.ui.screens.signup

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val address: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
)