package org.haidy.support.ui.screens.chat

sealed class ChatUiEffect {
    data object NavigateUp : ChatUiEffect()
}
