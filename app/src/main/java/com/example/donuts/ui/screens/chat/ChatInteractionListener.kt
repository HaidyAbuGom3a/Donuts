package com.example.donuts.ui.screens.chat

interface ChatInteractionListener {
    fun onMessageChanged(message: String)
    fun onClickSend()
    fun onClickBackIcon()
}