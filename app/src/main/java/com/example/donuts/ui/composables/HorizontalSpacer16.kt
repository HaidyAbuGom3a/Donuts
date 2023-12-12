package com.example.donuts.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.donuts.ui.spacing

@Composable
fun HorizontalSpacer16(){
    Spacer(modifier = Modifier.width(MaterialTheme.spacing.spacing_16))
}