package com.example.donuts.ui.screens.onboarding

sealed class OnBoardingUiEffect{
    data object NavigateToLogin: OnBoardingUiEffect()
}
