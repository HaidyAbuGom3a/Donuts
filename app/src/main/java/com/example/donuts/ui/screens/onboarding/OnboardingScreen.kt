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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.donuts.Composables.PrimaryButton
import com.example.donuts.R
import com.example.donuts.ui.dimens
import com.example.donuts.ui.screens.home.navigateToHome
import com.example.donuts.ui.spacing
import com.example.donuts.ui.theme.Black
import com.example.donuts.ui.theme.Primary100
import com.example.donuts.ui.theme.Typography
import com.example.donuts.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable()
fun OnBoardingScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Primary100, darkIcons = true)
    OnBoardingContent() { navController.navigateToHome() }
}

@Composable
fun OnBoardingContent(onClickButton: () -> Unit) {
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
                stringResource(R.string.gonuts_with_donuts),
                modifier = Modifier.padding(start = MaterialTheme.spacing.spacing_32, top = 340.dp),
                style = Typography.headlineLarge
            )
        }
        Text(
            stringResource(R.string.onboarding_description),
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.spacing_40)
                .padding(top = MaterialTheme.spacing.spacing_24),
            style = Typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            onClick = onClickButton,
            colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = Black),
            text = stringResource(R.string.get_started),
            contentPadding = PaddingValues(MaterialTheme.spacing.spacing_16),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.spacing_32)
                .padding(bottom = MaterialTheme.spacing.spacing_32),
        )

    }
}

@Preview(showSystemUi = true)
@Composable
fun previewOnBoarding() {
    OnBoardingContent() {}
}