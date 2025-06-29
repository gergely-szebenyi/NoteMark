package com.prekogdevs.notemark.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

val LightColorTheme = lightColorScheme(
    primary = Primary,
    surface = Surface,
    surfaceContainerLowest = SurfaceLowest,
    background = Background,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant
)

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(15.dp)
)

@Composable
fun NoteMarkTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorTheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}