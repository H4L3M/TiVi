@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package ma.nokhbativi.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ma.nokhbativi.R
import ma.nokhbativi.ads.UnityAdManager
import ma.nokhbativi.connectivity.ConnectivityObserver
import ma.nokhbativi.connectivity.NetworkConnectivityObserver
import ma.nokhbativi.ui.list.ListChannels
import ma.nokhbativi.ui.list.ListCountries
import ma.nokhbativi.ui.list.ListLiveEvents
import ma.nokhbativi.ui.navigation.NavBar
import ma.nokhbativi.ui.navigation.Screen
import ma.nokhbativi.ui.navigation.screens
import ma.nokhbativi.ui.screen.HomeScreen
import ma.nokhbativi.ui.theme.TiViTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: NetworkConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            connectivityObserver = NetworkConnectivityObserver(this)

            val status by connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserver.Status.Unavailable
            )

//            when(status){
//                ConnectivityObserver.Status.Unavailable -> {
//                    Toast.makeText(this, "Unavailable", Toast.LENGTH_SHORT).show()
//                }
//                ConnectivityObserver.Status.Available -> {
//                    Toast.makeText(this, "Available", Toast.LENGTH_SHORT).show()
//                }
//                ConnectivityObserver.Status.Losing -> {
//                    Toast.makeText(this, "Losing", Toast.LENGTH_SHORT).show()
//                }
//                ConnectivityObserver.Status.Lost -> {
//                    Toast.makeText(this, "Lost", Toast.LENGTH_SHORT).show()
//                }
//            }
            TiViTheme {
                TiViApp()
            }
        }
    }
}

@Composable
fun TiViApp() {

    var mCode by rememberSaveable { mutableStateOf("") }
    var mName by rememberSaveable { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetContent = {
            BottomSheetTopAppBar(sheetState, coroutineScope, mName)
            ListChannels(code = mCode)
        },
        scrimColor = MaterialTheme.colorScheme.scrim
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            contentColor = MaterialTheme.colorScheme.surface,
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
//                    actions = {
//                        val isDark by readSettings(context).collectAsState(initial = false)
//                        Switch(
//                            checked = isDark,
//                            onCheckedChange = {
////                            checked = it
//                                coroutineScope.launch {
//                                    setDarkMode(context = context, isDark = it)
//                                }
//                            },
//                            thumbContent = { Icon(imageVector = Icons.Rounded.Close, contentDescription = null)}
//                        )
//                    }
                )
            },
            bottomBar = {
                NavBar(navController = navController)
            }) { paddingValues ->

            BackHandler(enabled = sheetState.isVisible) {
                coroutineScope.launch {
                    sheetState.hide()
                }
            }

            NavHost(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .clipToBounds(),
                navController = navController,
                startDestination = Screen.Home.route
            ) {
                screens.forEach { screen ->
                    composable(route = screen.route) {
                        when (screen.route) {
                            Screen.Home.route -> {
                                HomeScreen { name, code ->
                                    mName = name
                                    mCode = code
                                    coroutineScope.launch {
                                        delay(50)
                                        sheetState.show()
                                    }
                                }
                            }

                            Screen.Countries.route -> {
                                ListCountries { name, code ->
                                    mName = name
                                    mCode = code
                                    coroutineScope.launch {
                                        delay(50)
                                        sheetState.show()
                                    }
                                }
                            }

                            Screen.LiveEvents.route -> {
                                ListLiveEvents()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomSheetTopAppBar(
    sheetState: ModalBottomSheetState, coroutineScope: CoroutineScope, name: String
) {
    TopAppBar(
        title = { Text(text = name) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutineScope.launch { sheetState.hide() }
                },
            ) { Icon(Icons.Rounded.Close, null) }
        },
    )
}