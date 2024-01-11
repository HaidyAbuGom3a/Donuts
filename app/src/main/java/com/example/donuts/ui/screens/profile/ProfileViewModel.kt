package com.example.donuts.ui.screens.profile

import android.util.Log
import com.example.donuts.domain.entities.User
import com.example.donuts.domain.usecases.ManageUserUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userUseCase: ManageUserUseCase) :
    BaseViewModel<ProfileUiState, ProfileUiEffect>(
        ProfileUiState()
    ), ProfileInteractionListener {

    init {
        getUserData()
    }

    private fun getUserData() {
        tryToExecute(
            { userUseCase.getUserData() },
            ::onGetUserDataSuccess,
            ::onError
        )
    }

    private fun onGetUserDataSuccess(user: User) {
        _state.update {
            it.copy(
                userName = user.username,
                email = user.email,
                imageUrl = user.imageUrl,
                address = user.address
            )
        }
    }

    private fun onError(error: Exception) {
        Log.i("error occurred", "$error")
    }

    override fun onClickBackIcon() {
        sendNewEffect(ProfileUiEffect.NavigateUp)
    }
}