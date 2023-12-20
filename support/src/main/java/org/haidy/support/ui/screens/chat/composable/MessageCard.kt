package org.haidy.support.ui.screens.chat.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.haidy.support.ui.screens.chat.MessageUIState
import org.haidy.support.ui.theme.CardBlue
import org.haidy.support.ui.theme.CardPink
import org.haidy.support.ui.theme.Grey200
import org.haidy.support.ui.theme.Secondary
import org.haidy.support.ui.theme.radius

@Composable
fun MessageCard(message: MessageUIState, showImage: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        horizontalArrangement = if (message.isMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AnimatedVisibility(visible = showImage) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    painter = rememberAsyncImagePainter(message.imageUrl),
                    contentDescription = "profile image",
                    contentScale = ContentScale.Crop
                )
            }
        }
        Text(
            text = message.message,
            style = MaterialTheme.typography.bodySmall,
            color = Secondary,
            modifier = Modifier
                .padding(start = 4.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = MaterialTheme.radius.radius_16,
                        topEnd = MaterialTheme.radius.radius_16,
                        bottomStart = if (message.isMe) MaterialTheme.radius.radius_16 else 0.dp,
                        bottomEnd = if (!message.isMe) MaterialTheme.radius.radius_16 else 0.dp
                    )
                )
                .background(if (message.isMe) CardPink else CardBlue)
                .border(
                    1.dp, color = Grey200, shape = RoundedCornerShape(
                        topStart = MaterialTheme.radius.radius_16,
                        topEnd = MaterialTheme.radius.radius_16,
                        bottomStart = if (message.isMe) MaterialTheme.radius.radius_16 else 0.dp,
                        bottomEnd = if (!message.isMe) MaterialTheme.radius.radius_16 else 0.dp
                    )
                )
                .padding(16.dp)

        )
    }
}