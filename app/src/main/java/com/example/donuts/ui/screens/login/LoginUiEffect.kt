package com.example.donuts.ui.screens.login

sealed class LoginUiEffect {
    data object NavigateToHome : LoginUiEffect()
    data object NavigateToSignUp : LoginUiEffect()
}
