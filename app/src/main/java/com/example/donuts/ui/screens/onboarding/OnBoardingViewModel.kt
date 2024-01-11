package com.example.donuts.ui.screens.onboarding

import com.example.donuts.domain.usecases.ManageUserUseCase
import com.example.donuts.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val manageUser: ManageUserUseCase) :
    BaseViewModel<Unit, OnBoardingUiEffect>(Unit), OnBoardingInteractionListener {
    override fun onClickGetStarted() {
        tryToExecute(
            { manageUser.saveIfFirstTimeUseApp(false) },
            {},
            {}
        )
        sendNewEffect(OnBoardingUiEffect.NavigateToLogin)
    }
}