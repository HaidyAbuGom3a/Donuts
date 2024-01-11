package org.haidy.support.ui.screens.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import org.haidy.support.domain.usecases.ManageUserUseCase
import org.haidy.support.domain.usecases.UserAuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.haidy.support.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val manageAuth: UserAuthenticationUseCase,
    private val manageUser: ManageUserUseCase
) :
    BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()), LoginInteractionListener {
    override fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun onClickEyeIcon() {
        val visibility = _state.value.passwordVisibility
        _state.update { it.copy(passwordVisibility = !visibility) }
    }

    override fun onClickLogin() {
        tryToExecute(
            { manageAuth.loginUser(state.value.email, state.value.password) },
            ::onLoginSuccess,
            ::onError
        )
    }

    private fun onLoginSuccess(id: String) {
        viewModelScope.launch {
            tryToExecute(
                { manageUser.saveUserId(id) },
                {},
                ::onError
            )
            tryToExecute(
                { manageUser.saveIfLoggedIn(true) },
                ::onSaveIsLoggedInSuccess,
                ::onError
            )
        }
    }

    private fun onSaveIsLoggedInSuccess(unit: Unit) {
        sendNewEffect(LoginUiEffect.NavigateToChat)
    }

    private fun onError(e: Exception) {
        Log.i("error occurred", "Error: ${e.localizedMessage}")
    }

    override fun onClickSignUp() {
        sendNewEffect(LoginUiEffect.NavigateToSignUp)
    }
}