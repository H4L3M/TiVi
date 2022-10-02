package com.nokhbativi.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector) {

    object Home : Screen(
        route = "Home",
        icon = Icons.Rounded.Home,
    )

    object Categories : Screen(
        route = "TiVi",
        icon = Icons.Rounded.Favorite,
    )

    object Account : Screen(
        route = "Search",
        icon = Icons.Rounded.Search,
    )

    object Channels : Screen(
        route = "Channels",
        icon = Icons.Rounded.Info,
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