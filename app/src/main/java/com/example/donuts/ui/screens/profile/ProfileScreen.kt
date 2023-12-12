package com.example.donuts.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.donuts.R
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.theme.BACKGROUND
import com.example.donuts.ui.theme.Black60
import com.example.donuts.ui.theme.Primary200
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.util.EffectHandler
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(BACKGROUND)
    systemUiController.setStatusBarColor(BACKGROUND, darkIcons = true)
    EffectHandler(effects = viewModel.effect) { effect ->
        when (effect) {
            ProfileUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    ProfileContent(state, viewModel)
}


@Composable
fun ProfileContent(state: ProfileUiState, listener: ProfileInteractionListener) {
    Column(modifier = Modifier.fillMaxSize().background(BACKGROUND))
    {
        Icon(
            painter = painterResource(id = R.drawable.icon_back),
            contentDescription = "",
            modifier = Modifier
                .padding(
                    start = 32.dp,
                    top = 32.dp,
                    bottom = 32.dp
                )
                .noRippleEffect { listener.onClickBackIcon() },
            tint = Primary300
        )


        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .border(4.dp, color = Primary200.copy(alpha = 0.3f), shape = CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(state.imageUrl),
                contentDescription = "profile image",
                contentScale = ContentScale.Crop
            )
        }

        TitleWithValue(
            title = "Username",
            value = state.userName,
            modifier = Modifier.padding(top = 64.dp, bottom = 16.dp)
        )

        HorizontalLine()

        TitleWithValue(
            title = "Email",
            value = state.email,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        HorizontalLine()

        TitleWithValue(
            title = "Address",
            value = state.address,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(Modifier.weight(1f))

    }
}

@Composable
private fun TitleWithValue(title: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            color = Primary200,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Composable
private fun HorizontalLine() {
    Spacer(
        Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Black60.copy(alpha = 0.2f))
            .padding(horizontal = 16.dp)
    )
}