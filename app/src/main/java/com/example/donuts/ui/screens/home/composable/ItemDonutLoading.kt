package com.example.donuts.ui.screens.home.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.donuts.ui.modifier.shimmerEffect
import com.example.donuts.ui.radius

@Composable
fun ItemDonutLoading() {
    Spacer(
        modifier = Modifier
            .padding(top = 40.dp, start = 8.dp, end = 8.dp)
            .width(138.dp)
            .height(100.dp)
            .clip(
                RoundedCornerShape(
                    topStart = MaterialTheme.radius.radius_20,
                    topEnd = MaterialTheme.radius.radius_20,
                    bottomStart = MaterialTheme.radius.radius_10,
                    bottomEnd = MaterialTheme.radius.radius_10
                )
            )
            .shimmerEffect()
    )
}