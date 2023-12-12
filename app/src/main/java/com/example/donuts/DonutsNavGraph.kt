package com.example.donuts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.donuts.ui.screens.details.detailsRoute
import com.example.donuts.ui.screens.home.homeScreenRoute
import com.example.donuts.ui.screens.login.loginScreenRoute
import com.example.donuts.ui.screens.onboarding.onBoardingRoute
import com.example.donuts.ui.screens.profile.profileRoute
import com.example.donuts.ui.screens.signup.signUpScreenRoute

const val START = "OnBoarding"

@Composable
fun DonutsNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = START) {
        onBoardingRoute(navController)
        homeScreenRoute(navController)
        detailsRoute(navController)
        loginScreenRoute(navController)
        signUpScreenRoute(navController)
        profileRoute(navController)
    }
}