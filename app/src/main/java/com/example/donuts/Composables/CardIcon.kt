package com.example.donuts.Composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import com.example.donuts.ui.spacing
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.theme.White

@Composable
fun <T> CardIcon(
    icon: Painter,
    shape: Shape,
    parameter: T? = null,
    modifier:Modifier = Modifier,
    onClickWithArgs: (T) -> Unit = {},
    onClick: () -> Unit = {},
    containerColor: Color = White,
    contentColor:Color = Primary300,
    contentPadding: Dp = MaterialTheme.spacing.spacing_8,
    elevation:CardElevation = CardDefaults.cardElevation(MaterialTheme.spacing.spacing_0)
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = modifier
            .clickable { if(parameter != null) onClickWithArgs(parameter) else onClick() },
        shape = shape,
        elevation = elevation
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier.padding(contentPadding)
        )
    }
}