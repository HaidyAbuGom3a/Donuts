package org.haidy.support.ui.screens.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
)
