package org.haidy.support.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.haidy.support.ui.screens.chat.chatRoute
import org.haidy.support.ui.screens.login.loginScreenRoute
import org.haidy.support.ui.screens.signup.signUpScreenRoute

@Composable
fun SupportNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController, startDestination = startDestination) {
        loginScreenRoute(navController)
        signUpScreenRoute(navController)
        chatRoute()
    }
}