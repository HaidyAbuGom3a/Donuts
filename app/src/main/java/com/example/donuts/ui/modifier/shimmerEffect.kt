package com.example.donuts.ui.modifier

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.example.donuts.ui.theme.CONTENT_BORDER

@Composable
fun Modifier.shimmerEffect(
    colors: List<Color> = listOf(
        CONTENT_BORDER,
        CONTENT_BORDER.copy(alpha = 0.2f),
        CONTENT_BORDER,
    ),
    durationMillis: Int = 2000,
): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "")
    val startOffset by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis),
        ),
        label = "",
    )

    background(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(startOffset, 0f),
            end = Offset(startOffset + size.width, size.height.toFloat()),
        )
    ).onGloballyPositioned { size = it.size }
}