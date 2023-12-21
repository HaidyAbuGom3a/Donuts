package com.example.donuts.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.donuts.ui.modifier.noRippleEffect
import com.example.donuts.ui.theme.Primary300

@Composable
fun DonutsBottomNavigation(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
       content()
    }

}

@Composable
fun DonutsBottomNavItem(icon: Int, onClick: () -> Unit) {
    Icon(
        painter = painterResource(id = icon),
        tint = Primary300,
        contentDescription = "",
        modifier = Modifier.noRippleEffect { onClick() }
    )
}