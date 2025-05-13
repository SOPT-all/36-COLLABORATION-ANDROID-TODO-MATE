package com.example.myapplication.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object TodomateTheme {
    val colors: TodomateColors
        @Composable
        @ReadOnlyComposable
        get() = LocalTodomateColorsProvider.current

    val typography: TodomateTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTodomateTypographyProvider.current
}

@Composable
fun ProvideTodomateColorsAndTypography(
    colors: TodomateColors,
    typography: TodomateTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTodomateColorsProvider provides colors,
        LocalTodomateTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun TodomateTheme(
    content: @Composable () -> Unit
) {
    ProvideTodomateColorsAndTypography(
        colors = defaultTodomateColors,
        typography = defaultTodomateTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = false
                }
            }
        }
        MaterialTheme(
            content = content
        )
    }
}