package org.haidy.support.ui.screens.signup

interface SignUpInteractionListener {
    fun onUsernameChanged(username: String)
    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
    fun onClickRegister()
    fun onClickBackIcon()
    fun onClickEyeIcon()
}