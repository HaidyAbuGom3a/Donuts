package org.haidy.support.ui.screens.login

sealed class LoginUiEffect {
    data object NavigateToChat : LoginUiEffect()
    data object NavigateToSignUp : LoginUiEffect()
}
