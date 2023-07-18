package com.example.donuts.Composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.donuts.ui.theme.Primary300

@Composable
fun BottomNavigation(icon1: Int, icon2: Int, icon3: Int, icon4: Int, icon5: Int) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(id = icon1),
            tint = Primary300,
            contentDescription = ""
        )
        Icon(
            painter = painterResource(id = icon2),
            tint = Primary300,
            contentDescription = ""
        )
        Icon(
            painter = painterResource(id = icon3),
            tint = Primary300,
            contentDescription = ""
        )
        Icon(
            painter = painterResource(id = icon4),
            tint = Primary300,
            contentDescription = ""
        )
        Icon(
            painter = painterResource(id = icon5),
            tint = Primary300,
            contentDescription = ""
        )
    }

}