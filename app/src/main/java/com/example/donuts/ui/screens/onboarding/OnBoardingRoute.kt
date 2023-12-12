package com.example.donuts.ui.screens.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "OnBoarding"

fun NavController.navigateToOnBoarding() {
    navigate(ROUTE)
}

fun NavGraphBuilder.onBoardingRoute(navController: NavController) {
    composable(ROUTE) { OnBoardingScreen(navController) }
}