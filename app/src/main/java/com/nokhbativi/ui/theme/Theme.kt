package com.nokhbativi.ui.theme

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
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF2D2C38),
    secondary = Color(0xFFFFDB43),
//    tertiary = Color.Blue,
    background = Color(0xFF2D2C38),
    surface = Color(0xFF3F3C49),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFF0F0F0),
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF0F0F0),
    secondary = Color(0xFF3E59F0),
//    tertiary = Color.Cyan,
    background = Color(0xFFF0F0F0),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)


@Composable
fun TiViTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
//    systemUiController.setSystemBarsColor(color = Color.Transparent)

    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
//    val view = LocalView.current
//    val window = (view.context as Activity).window
//    if (!view.isInEditMode) {
//        SideEffect {
////            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
//                darkTheme
//        }
//    }

//    if (darkTheme) {
        val useDarkIcons = !isSystemInDarkTheme()
        SideEffect {
            // Update all of the system bar colors to be transparent, and use
            // dark icons if we're in light theme
            systemUiController.setSystemBarsColor(
                color = if (darkTheme) DarkColorScheme.primary else LightColorScheme.primary,
//                color = DarkColorScheme.primary,
                darkIcons = useDarkIcons
            )
        }
//    }

//    DisposableEffect(systemUiController, useDarkIcons) {
//        // Update all of the system bar colors to be transparent, and use
//        // dark icons if we're in light theme
//        systemUiController.setSystemBarsColor(
//            color = Color.Transparent,
//            darkIcons = useDarkIcons
//        )
//
//        // setStatusBarColor() and setNavigationBarColor() also exist
//
//        onDispose {}
//    }

//    if (darkTheme) {
//        systemUiController.setSystemBarsColor(
//            color = Color.Transparent
//        )
//    } else {
//        systemUiController.setSystemBarsColor(
//            color = Color.Transparent
//        )
//    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}