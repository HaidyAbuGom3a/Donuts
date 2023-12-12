package com.example.donuts.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.donuts.R
import com.example.donuts.ui.composables.DonutsButton
import com.example.donuts.ui.composables.DonutsTextField
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.screens.home.navigateToHome
import com.example.donuts.ui.screens.signup.navigateToSignUp
import com.example.donuts.ui.theme.BACKGROUND
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.util.EffectHandler
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(BACKGROUND)
    systemUiController.setStatusBarColor(BACKGROUND, darkIcons = true)
    EffectHandler(effects = viewModel.effect) { effect ->
        when (effect) {
            LoginUiEffect.NavigateToHome -> navController.navigateToHome()
            LoginUiEffect.NavigateToSignUp -> navController.navigateToSignUp(true)
        }
    }
    LoginContent(state = state, viewModel)

}

@Composable
fun LoginContent(state: LoginUiState, listener: LoginInteractionListener) {
    Column(modifier = Modifier.fillMaxSize().background(BACKGROUND)) {
        Text(
            "Login",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            color = Primary300
        )

        Spacer(modifier = Modifier.weight(1f))

        DonutsTextField(
            modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp),
            text = state.email,
            hint = "Email",
            onValueChange = { listener.onEmailChanged(it) },
        )

        DonutsTextField(
            modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp),
            text = state.password,
            hint = "Password",
            keyboardType = KeyboardType.Password,
            onValueChange = { listener.onPasswordChanged(it) },
            trailingPainter = painterResource(
                id = if (state.passwordVisibility) R.drawable.disabled_eye_icon
                else R.drawable.eye_icon
            ),
            onTrailingIconClick = { listener.onClickEyeIcon() },
            showPassword = state.passwordVisibility
        )

        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = "Don't have account?",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.noRippleEffect { listener.onClickSignUp() },
                text = "Sign Up",
                style = MaterialTheme.typography.bodyMedium,
                color = Primary300
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        DonutsButton(
            onClick = { listener.onClickLogin() },
            text = "Login",
            enabled = isButtonEnabled(state),
            modifier = Modifier.padding(bottom = 48.dp, end = 16.dp, start = 16.dp)
        )

    }
}

private fun isButtonEnabled(state: LoginUiState): Boolean {
    return state.email.isNotEmpty() && state.password.isNotEmpty()
}

@Composable
@Preview(showSystemUi = false)
fun PreviewLogin() {

}

