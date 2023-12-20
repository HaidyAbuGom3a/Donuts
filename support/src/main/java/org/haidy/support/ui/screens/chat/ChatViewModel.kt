package org.haidy.support.ui.screens.chat

import android.util.Log
import org.haidy.support.domain.entities.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.support.domain.usecases.ManageChatUseCaseImp
import org.haidy.support.domain.usecases.ManageUserUseCase
import org.haidy.support.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val manageChat: ManageChatUseCaseImp,
    private val manageUser: ManageUserUseCase
) :
    BaseViewModel<ChatUIState, ChatUiEffect>(ChatUIState()), ChatInteractionListener {

    init {
        getAvailableChat()
    }

    private fun getAvailableChat() {
        tryToCollect(
            { manageChat.getAvailableChat() },
            ::onGetAvailableChatSuccess,
            ::onError
        )
    }

    private fun onGetAvailableChatSuccess(chatId: String) {
        _state.update { it.copy(chatId = chatId) }
        tryToCollect(
            { manageChat.getMessages(chatId) },
            ::onGetMessagesSuccess,
            ::onError
        )
    }

    private fun getUserImageUrl() {
        tryToExecute(
            { manageUser.getUserData() },
            { _state.update { it.copy(imageUrl = it.imageUrl) } },
            ::onError
        )
    }

    override fun onMessageChanged(message: String) {
        _state.update { it.copy(message = message) }
    }

    override fun onClickSend() {
        val state = state.value
        val message = state.message
        _state.update { it.copy(message = "") }
        tryToExecute(
            {
                manageChat.sendMessage(
                    chatId = state.chatId,
                    message = message,
                    imageUrl = state.imageUrl
                )
            },
            ::onSendMessageSuccess,
            ::onError
        )
    }

    private fun onSendMessageSuccess(message: Message) {
        _state.update { it.copy(chatId = message.chatId) }
    }

    private fun onGetMessagesSuccess(messages: List<Message>) {
        _state.update { it.copy(messages = messages.map { message -> message.toUIState() }) }
    }

    private fun onError(e: Exception) {
        Log.i("error occurred", e.toString())
    }


    override fun onClickBackIcon() {
        sendNewEffect(ChatUiEffect.NavigateUp)
    }


}