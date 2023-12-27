package com.example.donuts.ui.screens.cart.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donuts.ui.composables.extension.bottomBorder
import com.example.donuts.ui.theme.Black60
import com.example.donuts.ui.theme.Primary200
import com.example.donuts.ui.theme.Secondary

@Composable
fun ItemCart(
    name: String,
    amount: Int,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
    borderVisible: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .bottomBorder(1.dp, Secondary.copy(alpha = 0.5f), borderVisible),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = imagePainter,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .width(84.dp)
                .height(92.dp)
        )
        Text(
            name,
            style = MaterialTheme.typography.titleMedium,
            color = Primary200,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            "Amount:",
            style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            amount.toString(),
            style = MaterialTheme.typography.bodyMedium.copy(color = Black60),
            modifier = Modifier.padding(end = 16.dp, bottom = 16.dp, top = 16.dp)
        )
    }
}