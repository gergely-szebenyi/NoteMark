package com.prekogdevs.notemark.presentation.auth.landing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object LandingCardDefaults {
    val Shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    val Padding = PaddingValues(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
    val SurfaceColor @Composable get() = MaterialTheme.colorScheme.surfaceContainerLowest
}
