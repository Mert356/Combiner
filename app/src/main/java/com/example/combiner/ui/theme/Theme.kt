package com.example.combiner.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryWhite,
    secondary = SecondaryBeige,
    background = PrimaryBlack,
    surface = SecondaryGray,
    onPrimary = PrimaryBlack,
    onSecondary = PrimaryBlack,
    onBackground = PrimaryWhite,
    onSurface = PrimaryWhite
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlack,
    secondary = SecondaryBeige,
    background = PrimaryWhite,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = PrimaryBlack,
    onBackground = PrimaryBlack,
    onSurface = PrimaryBlack
)

@Composable
fun CombinerTheme(
    darkTheme: Boolean = false, // Karanlık mod desteği
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
