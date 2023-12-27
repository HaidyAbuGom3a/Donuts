package org.haidy.support.ui.screens.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.haidy.support.ui.navigation.SupportDestination

private const val ROUTE = SupportDestination.SIGN_UP

fun NavController.navigateToSignUp(clearBackStack: Boolean = false) {
    navigate(ROUTE) {
        if (clearBackStack) {
            popUpTo(ROUTE) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.signUpScreenRoute(navController: NavController) {
    composable(ROUTE) { SignUpScreen(navController = navController) }
}