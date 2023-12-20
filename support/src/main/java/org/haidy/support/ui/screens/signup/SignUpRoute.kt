package org.haidy.support.ui.screens.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "SignUp"

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