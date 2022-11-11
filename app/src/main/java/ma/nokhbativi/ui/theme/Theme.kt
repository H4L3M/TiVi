package ma.nokhbativi.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.Placeholder
import com.google.accompanist.systemuicontroller.rememberSystemUiController


val DarkColorScheme = darkColorScheme(
//    primary = Color(0xFF2D2C38),
    primary = Color.Black,
    secondary = Color(0xFFFFDB43),
//    tertiary = Color.Blue,
    background = Color.Black,
//    background = Color(0xFF2D2C38),
    surface = Color(0xFF111111),
//    surface = Color(0xFF3F3C49),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFF0F0F0),
    onSurface = Color.White,
    scrim = Color(0xA1000000)
)

val LightColorScheme = lightColorScheme(
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
    scrim = Color(0xA1FFFFFF)
)


@Composable
fun TiViTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val useDarkIcons = !isSystemInDarkTheme()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (darkTheme) DarkColorScheme.primary else LightColorScheme.primary,
            darkIcons = useDarkIcons,
            isNavigationBarContrastEnforced = true
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun ChangeStatusBarIconsColor() {
    //change color of status bar icons.
    val systemUiController = rememberSystemUiController()
    var clickedColor by remember { mutableStateOf(Color.Unspecified) }
    var statusBarDarkIcons by remember { mutableStateOf(false) }
    val navigationBarDarkIcons by remember { mutableStateOf(false) }

    LaunchedEffect(systemUiController, statusBarDarkIcons, navigationBarDarkIcons) {
        systemUiController.statusBarDarkContentEnabled = statusBarDarkIcons
        systemUiController.navigationBarDarkContentEnabled = navigationBarDarkIcons
    }

    //reverse system bar color
    statusBarDarkIcons = clickedColor.luminance() > 0.5f
}