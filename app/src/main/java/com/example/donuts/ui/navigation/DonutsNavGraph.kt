package com.example.donuts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.donuts.ui.screens.cart.cartRoute
import com.example.donuts.ui.screens.chat.chatRoute
import com.example.donuts.ui.screens.details.detailsRoute
import com.example.donuts.ui.screens.favorite.favoriteRoute
import com.example.donuts.ui.screens.home.homeScreenRoute
import com.example.donuts.ui.screens.login.loginScreenRoute
import com.example.donuts.ui.screens.onboarding.onBoardingRoute
import com.example.donuts.ui.screens.profile.profileRoute
import com.example.donuts.ui.screens.signup.signUpScreenRoute


@Composable
fun DonutsNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController, startDestination = startDestination) {
        onBoardingRoute(navController)
        loginScreenRoute(navController)
        signUpScreenRoute(navController)
        homeScreenRoute(navController)
        detailsRoute(navController)
        profileRoute(navController)
        chatRoute(navController)
        cartRoute(navController)
        favoriteRoute(navController)
    }
}