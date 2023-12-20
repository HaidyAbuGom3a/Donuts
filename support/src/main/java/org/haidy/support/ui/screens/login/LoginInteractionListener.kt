package org.haidy.support.ui.screens.login

interface LoginInteractionListener {
    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
    fun onClickEyeIcon()
    fun onClickLogin()
    fun onClickSignUp()

}