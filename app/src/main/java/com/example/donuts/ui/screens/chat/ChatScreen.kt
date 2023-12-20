package com.example.donuts.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.donuts.R
import com.example.donuts.ui.composables.DonutsTextField
import com.example.donuts.ui.screens.chat.composable.MessageCard
import com.example.donuts.ui.theme.BACKGROUND
import com.example.donuts.ui.util.EffectHandler
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.abs

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel(), navController: NavController) {
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(BACKGROUND)
    systemUiController.setStatusBarColor(BACKGROUND, darkIcons = true)
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
        bottomBar = {
            DonutsTextField(
                text = state.message,
                onValueChange = { listener.onMessageChanged(it) },
                hint = "Message",
                trailingIconEnabled = state.message.isNotEmpty(),
                isSingleLine = false,
                trailingPainter = painterResource(R.drawable.send_icon),
                onTrailingIconClick = { listener.onClickSend() },
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom= 16.dp)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BACKGROUND),
            state = scrollState,
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding() + 16.dp,
                bottom = paddingValues.calculateBottomPadding() + 8.dp
            ),
            verticalArrangement = remember {
                object : Arrangement.Vertical {
                    override fun Density.arrange(
                        totalSize: Int,
                        sizes: IntArray,
                        outPositions: IntArray
                    ) {
                        var currentOffset = 0
                        sizes.forEachIndexed { index, size ->
                            outPositions[sizes.lastIndex - index] = totalSize - currentOffset - size
                            currentOffset += size
                        }
                    }
                }
            },
            horizontalAlignment = if (state.messages.isEmpty()) Alignment.CenterHorizontally else Alignment.Start
        ) {
//        item {
//            AnimatedVisibility(state.messages.isEmpty()) {
//                Image(
//                    painterResource(),
//                    contentDescription = null
//                )
//
//            }
//        }
//        item {
//            AnimatedVisibility(state.messages.isEmpty()) {
//                Text(
//                    Resources.strings.sendMessageToStartLiveChat,
//                    style = Theme.typography.caption,
//                    color = Theme.colors.contentTertiary
//                )
//
//            }
//        }
            items(state.messages) { message ->
                MessageCard(message)
            }
        }
    }
}