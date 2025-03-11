package com.example.combiner.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = BeigeMocha,
    secondary = Espresso,
    background = DarkTaupe,
    surface = Espresso,
    onPrimary = SoftIvory,
    onSecondary = GoldenAmber,
    onBackground = SoftIvory,
    onSurface = SoftIvory
)

private val LightColorScheme = lightColorScheme(
    primary = SecondaryTextAndIconColor,
    secondary = BackgroundColor,
    background = PostBackgroundColor,
    surface = GoldenSand,
    onPrimary = TextAndIconColor,
    onSecondary = GoldenSand,
    onBackground = TextAndIconColor,
    onSurface = TextAndIconColor
)

@Composable
fun CombinerTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
