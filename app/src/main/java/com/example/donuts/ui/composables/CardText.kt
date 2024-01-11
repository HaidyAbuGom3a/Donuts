package com.example.donuts.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.spacing
import com.example.donuts.ui.theme.Primary300
import com.example.donuts.ui.theme.White


@Composable
fun CardText(
    text: String,
    shape: Shape,
    modifier: Modifier = Modifier,
    isClickable:Boolean,
    onClick: () -> Unit = {},
    containerColor: Color = White,
    contentColor: Color = Primary300,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.spacing_8)
) {
    val newModifier = if(isClickable) modifier.noRippleEffect { onClick() } else modifier
    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = newModifier,
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.spacing.spacing_1)
    ) {
        Text(
            text,
            modifier = Modifier.padding(contentPadding),
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
    }
}