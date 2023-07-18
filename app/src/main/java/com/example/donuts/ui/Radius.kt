package com.example.donuts.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Radius(
    val radius_8: Dp = 8.dp,
    val radius_10: Dp = 10.dp,
    val radius_15: Dp = 15.dp,
    val radius_16: Dp = 16.dp,
    val radius_20: Dp = 20.dp,
    val radius_24: Dp = 24.dp,
    val radius_40: Dp = 40.dp,
)

val LocalRadius = compositionLocalOf { Radius() }

val MaterialTheme.radius: Radius
    @Composable
    @ReadOnlyComposable
    get() = LocalRadius.current