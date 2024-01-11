package com.example.donuts.ui.screens.home.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
fun ItemDonutOfferLoading(){
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .width(173.dp)
            .clip(
                shape = RoundedCornerShape(MaterialTheme.radius.radius_20)
            )
            .shimmerEffect()
    )
}