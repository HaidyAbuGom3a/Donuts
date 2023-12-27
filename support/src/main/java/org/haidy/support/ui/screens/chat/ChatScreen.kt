package org.haidy.support.ui.screens.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.haidy.support.R
import org.haidy.support.ui.composables.DonutsButton
import org.haidy.support.ui.composables.DonutsTextField
import org.haidy.support.ui.screens.chat.composable.MessageCard
import org.haidy.support.ui.theme.BACKGROUND
import org.haidy.support.ui.theme.Primary300
import org.haidy.support.ui.theme.Red
import org.haidy.support.ui.theme.White
import kotlin.math.abs

@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(BACKGROUND)
    systemUiController.setStatusBarColor(BACKGROUND, darkIcons = true)
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
            Row(
                modifier = Modifier.padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                DonutsButton(
                    onClick = { listener.onClickCloseChat() },
                    text = "Close Chat",
                    contentColor = Red,
                    containerColor = White,
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
                iconTint = Primary300,
                onTrailingIconClick = { listener.onClickSend() },
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
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
            verticalArrangement = if (state.messages.isEmpty()) Arrangement.Center else Arrangement.Bottom,
            horizontalAlignment = if (state.messages.isEmpty()) Alignment.CenterHorizontally else Alignment.Start
        ) {
            item {
                AnimatedVisibility(state.messages.isEmpty()) {
                    Image(
                        painterResource(R.drawable.chat_placeholder),
                        contentDescription = null,
                        modifier = Modifier.padding(16.dp)
                    )

                }
            }
            item {
                AnimatedVisibility(state.messages.isEmpty()) {
                    Text(
                        "No messages yet!",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                        modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
                    )

                }
            }
            items(state.messages) { message ->
                MessageCard(message)
            }
        }
    }
}