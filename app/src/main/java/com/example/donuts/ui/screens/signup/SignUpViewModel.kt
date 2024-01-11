package com.example.donuts.ui.screens.signup

import android.util.Log
import com.example.donuts.domain.usecases.UserAuthenticationUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val manageAuth: UserAuthenticationUseCase) :
    BaseViewModel<SignUpUiState, SignUpUiEffect>(
        SignUpUiState()
    ), SignUpInteractionListener {
    override fun onUsernameChanged(username: String) {
        _state.update { it.copy(username = username) }
    }

    override fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun onAddressChanged(address: String) {
        _state.update { it.copy(address = address) }
    }

    override fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun onClickRegister() {
        tryToExecute(
            {
                manageAuth.signUpUser(
                    state.value.username,
                    state.value.email,
                    state.value.password,
                    state.value.address
                )
            },
            ::onSignUpSuccess,
            ::onError
        )
    }

    private fun onSignUpSuccess(value: Boolean) {
        sendNewEffect(SignUpUiEffect.NavigateToLogin)
    }

    private fun onError(e: Exception) {
        Log.i("error occurred", "Error: ${e.localizedMessage}")
    }

    override fun onClickBackIcon() {
        sendNewEffect(SignUpUiEffect.NavigateUp)
    }

    override fun onClickEyeIcon() {
        val visibility = _state.value.passwordVisibility
        _state.update { it.copy(passwordVisibility = !visibility) }
    }

}