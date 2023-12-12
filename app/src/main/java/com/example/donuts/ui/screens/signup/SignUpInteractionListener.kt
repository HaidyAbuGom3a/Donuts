package com.example.donuts.ui.screens.signup

interface SignUpInteractionListener {
    fun onUsernameChanged(username: String)
    fun onEmailChanged(email: String)
    fun onAddressChanged(address: String)
    fun onPasswordChanged(password: String)
    fun onClickRegister()
    fun onClickBackIcon()
    fun onClickEyeIcon()
}