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
    primary = Caramel,
    secondary = BeigeCream,
    background = WarmGray,
    surface = GoldenSand,
    onPrimary = DeepChocolate,
    onSecondary = GoldenSand,
    onBackground = DeepChocolate,
    onSurface = DeepChocolate
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
