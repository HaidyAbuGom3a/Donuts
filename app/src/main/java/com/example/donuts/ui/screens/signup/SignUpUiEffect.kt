package com.example.donuts.ui.screens.signup

sealed class SignUpUiEffect {
    data object NavigateToLogin: SignUpUiEffect()
    data object NavigateUp: SignUpUiEffect()
}