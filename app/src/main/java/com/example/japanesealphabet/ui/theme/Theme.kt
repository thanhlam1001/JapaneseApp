package com.example.japanesealphabet.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Matcha,
    onPrimary = Cream,
    primaryContainer = Sand,
    onPrimaryContainer = Ink,
    secondary = Coral,
    secondaryContainer = ColorTokens.secondaryContainer,
    tertiaryContainer = ColorTokens.tertiaryContainer,
    background = Cream,
    surface = androidx.compose.ui.graphics.Color.White,
    surfaceVariant = Mist,
    onSurface = Ink,
    onSurfaceVariant = Ink,
    outlineVariant = ColorTokens.outline
)

private val DarkColors = darkColorScheme(
    primary = Sand,
    onPrimary = Pine,
    primaryContainer = Pine,
    onPrimaryContainer = Cream,
    secondary = Coral,
    secondaryContainer = Matcha,
    tertiaryContainer = Pine,
    background = Ink,
    surface = Pine,
    onSurface = Cream,
    onSurfaceVariant = Cream
)

private object ColorTokens {
    val secondaryContainer = androidx.compose.ui.graphics.Color(0xFFFFE2D9)
    val tertiaryContainer = androidx.compose.ui.graphics.Color(0xFFE0EBD9)
    val outline = androidx.compose.ui.graphics.Color(0xFFD8D1C2)
}

@Composable
fun JapaneseAlphabetTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
