package com.example.donuts.ui.screens.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.donuts.R
import com.example.donuts.ui.composables.DonutsTextField
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.screens.chat.composable.MessageCard
import com.example.donuts.ui.theme.BACKGROUND
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.util.EffectHandler
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.abs

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel(), navController: NavController) {
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(BACKGROUND, darkIcons = true)
    systemUiController.setNavigationBarColor(BACKGROUND, darkIcons = true)
    EffectHandler(effects = viewModel.effect) { effect ->
        when (effect) {
            ChatUiEffect.NavigateUp -> navController.popBackStack()
        }
    }
    ChatContent(state, viewModel)
}


@Composable
fun ChatContent(state: ChatUIState, listener: ChatInteractionListener) {
    val scrollState = rememberLazyListState()
    LaunchedEffect(state.messages.size) {
        scrollState.animateScrollToItem(abs(state.messages.size - 1))
    }
    Scaffold(
        topBar = {
            Box {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            top = 24.dp,
                            bottom = 24.dp
                        )
                        .size(24.dp)
                        .noRippleEffect { listener.onClickBackIcon() },
                    tint = Primary300
                )
                Text(
                    text = "Customer Support",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
        },
        bottomBar = {
            DonutsTextField(
                text = state.message,
                onValueChange = { listener.onMessageChanged(it) },
                hint = "Message",
                trailingIconEnabled = state.message.isNotEmpty(),
                isSingleLine = false,
                trailingPainter = painterResource(R.drawable.send_icon),
                onTrailingIconClick = { listener.onClickSend() },
                iconTint = Primary300,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BACKGROUND)
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
            state = scrollState,
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 8.dp
            ),
            verticalArrangement = if (state.messages.isEmpty()) Arrangement.Center else Arrangement.Bottom,
            horizontalAlignment = if (state.messages.isEmpty()) Alignment.CenterHorizontally else Alignment.Start
        ) {
            item {
                AnimatedVisibility(state.messages.isEmpty()) {
                    Image(
                        painterResource(R.drawable.chat_placeholder),
                        contentDescription = null,
                        modifier = Modifier.padding(40.dp)
                    )

                }
            }
            item {
                AnimatedVisibility(state.messages.isEmpty()) {
                    Text(
                        "Send message to start new chat",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                }
            }
            items(state.messages) { message ->
                MessageCard(message)
            }
        }
    }
}
