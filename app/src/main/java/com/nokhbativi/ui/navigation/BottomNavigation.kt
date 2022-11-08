package com.nokhbativi.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


val screens = listOf(
    Screen.Home,
    Screen.Countries,
    Screen.LiveEvents
)

@Composable
fun NavBar(navController: NavController) {

    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                label = { Text(text = screen.route) },
                icon = { Icon(painter =screen.icon(selected), contentDescription = null) },
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun Prev() {
    NavBar(navController = rememberNavController())
}