package com.forensic.drugs.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = FlagBlue,
    onPrimary = FlagWhite,
    primaryContainer = FlagBlueSoft,
    onPrimaryContainer = FlagBlueDark,
    secondary = FlagRed,
    onSecondary = FlagWhite,
    secondaryContainer = FlagRedSoft,
    onSecondaryContainer = FlagRedDark,
    tertiary = FlagBlueLight,
    onTertiary = FlagWhite,
    background = BackgroundLight,
    onBackground = TextDark,
    surface = SurfaceLight,
    onSurface = TextDark,
    surfaceVariant = FlagWhiteSoft,
    onSurfaceVariant = TextGrey,
    outline = FlagBlue.copy(alpha = 0.3f)
)

private val DarkColors = darkColorScheme(
    primary = FlagBlueLight,
    onPrimary = FlagWhite,
    primaryContainer = FlagBlueDark,
    onPrimaryContainer = FlagWhite,
    secondary = FlagRedLight,
    onSecondary = FlagWhite,
    secondaryContainer = FlagRedDark,
    onSecondaryContainer = FlagWhite,
    tertiary = FlagBlue,
    onTertiary = FlagWhite,
    background = DarkBackground,
    onBackground = DarkText,
    surface = DarkSurface,
    onSurface = DarkText,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkTextMuted,
    outline = FlagBlueLight.copy(alpha = 0.4f)
)

@Composable
fun ForensicDrugsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
