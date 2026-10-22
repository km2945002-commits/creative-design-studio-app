package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val StudioColorScheme = darkColorScheme(
    primary = AmberGold,
    onPrimary = Color(0xFF1E1400),
    primaryContainer = Color(0xFF382A00),
    onPrimaryContainer = AmberGoldLight,
    secondary = NeonRose,
    onSecondary = Color(0xFF330018),
    secondaryContainer = Color(0xFF5B002F),
    onSecondaryContainer = NeonRoseLight,
    tertiary = ElectricViolet,
    onTertiary = Color(0xFF1F0053),
    tertiaryContainer = Color(0xFF3B1578),
    onTertiaryContainer = ElectricVioletLight,
    background = StudioDarkBg,
    onBackground = TextPrimary,
    surface = StudioDarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = StudioCardBg,
    onSurfaceVariant = TextSecondary,
    outline = StudioCardBorder,
    outlineVariant = Color(0xFF333852)
)

@Composable
fun CreativeDesignStudioTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = StudioColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = StudioDarkBg.toArgb()
                window.navigationBarColor = StudioDarkBg.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
