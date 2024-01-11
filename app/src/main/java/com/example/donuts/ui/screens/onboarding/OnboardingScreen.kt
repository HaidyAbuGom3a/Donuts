package com.example.donuts.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.donuts.R
import com.example.donuts.ui.composables.PrimaryButton
import com.example.donuts.ui.dimens
import com.example.donuts.ui.screens.login.navigateToLogin
import com.example.donuts.ui.spacing
import com.example.donuts.ui.theme.Black
import com.example.donuts.ui.theme.Primary100
import com.example.donuts.ui.theme.Typography
import com.example.donuts.ui.theme.White
import com.example.donuts.ui.util.EffectHandler
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun OnBoardingScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Primary100, darkIcons = true)
    EffectHandler(effects = viewModel.effect) { effect ->
        when (effect) {
            OnBoardingUiEffect.NavigateToLogin -> navController.navigateToLogin()
        }
    }
    OnBoardingContent(viewModel)
}

@Composable
fun OnBoardingContent(listener: OnBoardingInteractionListener) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary100)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.dimens_530)
        ) {
            Image(
                painter = painterResource(id = R.drawable.donuts_clipped),
                contentDescription = "", modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.dimens_435),
                contentScale = ContentScale.FillBounds
            )
            Text(
                "Gonuts\nwith\nDonuts",
                modifier = Modifier.padding(start = MaterialTheme.spacing.spacing_32, top = 340.dp),
                style = Typography.headlineLarge
            )
        }
        Text(
            "Gonuts with Donuts is a Sri Lanka dedicated food outlets for specialize manufacturing of Donuts in Colombo, Sri Lanka.",
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .padding(top = 24.dp),
            style = Typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            onClick = { listener.onClickGetStarted() },
            colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = Black),
            text = "Get Started",
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 32.dp),
        )

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOnBoarding() {
    val viewModel: OnBoardingViewModel = hiltViewModel()
    OnBoardingContent(viewModel)
}