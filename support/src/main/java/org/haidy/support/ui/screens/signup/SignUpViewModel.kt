package org.haidy.support.ui.screens.signup

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import org.haidy.support.domain.usecases.UserAuthenticationUseCase
import org.haidy.support.ui.base.BaseViewModel
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


    override fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun onClickRegister() {
        tryToExecute(
            {
                manageAuth.signUpUser(
                    state.value.username,
                    state.value.email,
                    state.value.password
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