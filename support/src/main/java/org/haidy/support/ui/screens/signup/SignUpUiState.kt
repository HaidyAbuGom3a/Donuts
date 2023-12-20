package org.haidy.support.ui.screens.signup

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
)