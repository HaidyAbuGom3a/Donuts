package com.example.donuts.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val dimens_530:Dp = 530.dp,
    val dimens_435:Dp = 435.dp,
    val dimens_280:Dp = 280.dp,
    val dimens_173:Dp = 173.dp,
    val dimens_138:Dp = 138.dp,
    val dimens_130:Dp = 130.dp,
    val dimens_112:Dp = 112.dp,
    val dimens_104:Dp = 104.dp

)

val LocalDimens = compositionLocalOf { Dimens() }

val MaterialTheme.dimens: Dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current