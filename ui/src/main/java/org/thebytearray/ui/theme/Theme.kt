package org.thebytearray.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Dark Theme
private val DarkColorScheme = darkColorScheme(
    primary = Neutral200,
    secondary = Neutral300,
    tertiary = Neutral400,
    background = Neutral900,
    surface = Neutral900,
    onPrimary = Neutral950,
    onSecondary = Neutral950,
    onTertiary = Neutral950,
    onBackground = Neutral50,
    onSurface = Neutral50
)

// Light Theme
private val LightColorScheme = lightColorScheme(
    primary = Neutral800,
    secondary = Neutral700,
    tertiary = Neutral600,
    background = Neutral50,
    surface = Neutral50,
    onPrimary = Neutral50,
    onSecondary = Neutral50,
    onTertiary = Neutral50,
    onBackground = Neutral900,
    onSurface = Neutral900
)


@Composable
fun ByteUITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}