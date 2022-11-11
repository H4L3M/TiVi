package ma.nokhbativi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import ma.nokhbativi.R

sealed class Screen(val route: String, val icon: @Composable (selected: Boolean) -> Painter) {

    object Home : Screen(
        route = "Home",
        icon = { if (it) painterResource(id = R.drawable.solid_home) else painterResource(id = R.drawable.outline_home) },
    )

    object Countries : Screen(
        route = "Countries",
        icon = { if (it) painterResource(id = R.drawable.solid_world) else painterResource(id = R.drawable.outline_world) },
    )

    object LiveEvents : Screen(
        route = "Events",
        icon = { if (it) painterResource(id = R.drawable.solid_whistle) else painterResource(id = R.drawable.outline_whistle) },
    )

    //if you want to send values
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}