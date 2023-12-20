package com.example.donuts.ui.screens.chat

import android.util.Log
import com.example.donuts.domain.entities.Message
import com.example.donuts.domain.usecases.ManageChatUseCaseImp
import com.example.donuts.domain.usecases.ManageUserUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val manageChat: ManageChatUseCaseImp,
    private val manageUser: ManageUserUseCase
) :
    BaseViewModel<ChatUIState, ChatUiEffect>(ChatUIState()), ChatInteractionListener {

    init {
        getUserImageUrl()
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
        //println("haidy send message success $message")
        _state.update { it.copy(chatId = message.chatId) }
        tryToCollect(
            { manageChat.getMessages(message.chatId) },
            ::onGetMessagesSuccess,
            ::onError
        )
    }

    private fun onGetMessagesSuccess(messages: List<Message>) {
        _state.update { it.copy(messages = messages.map { message -> message.toUIState() }) }
        println("haidy updated messages state ${state.value.messages}")
    }

    private fun onError(e: Exception) {
        Log.i("error occurred", e.toString())
    }


    override fun onClickBackIcon() {
        sendNewEffect(ChatUiEffect.NavigateUp)
    }


}