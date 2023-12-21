package com.example.donuts.ui.screens.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "Login"

fun NavController.navigateToLogin() {
    popBackStack()
    navigate(ROUTE)
}

fun NavGraphBuilder.loginScreenRoute(navController: NavController) {
    composable(ROUTE) { LoginScreen(navController = navController) }
}