package com.example.donuts.ui.screens.cart.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.donuts.ui.modifier.shimmerEffect

@Composable
fun ItemCartLoading(){
    Box(
      modifier = Modifier.fillMaxWidth().height(112.dp).padding(16.dp).shimmerEffect()
    )
}