package org.haidy.support.ui.screens.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.support.ui.navigation.SupportDestination

private const val ROUTE = SupportDestination.LOGIN

fun NavController.navigateToLogin() {
    popBackStack()
    navigate(ROUTE)
}

fun NavGraphBuilder.loginScreenRoute(navController: NavController) {
    composable(ROUTE) { LoginScreen(navController = navController) }
}