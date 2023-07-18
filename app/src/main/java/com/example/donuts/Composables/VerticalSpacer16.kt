package com.example.donuts.Composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.donuts.ui.spacing

@Composable
fun VerticalSpacer16(){
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing_16))
}