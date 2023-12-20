package org.haidy.support.ui.screens.chat

interface ChatInteractionListener {
    fun onMessageChanged(message: String)
    fun onClickSend()
    fun onClickBackIcon()
}