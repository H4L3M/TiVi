@file:OptIn(ExperimentalMaterial3Api::class)

package com.nokhbativi.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.nokhbativi.R
import com.nokhbativi.ui.navigation.NavBar
import com.nokhbativi.ui.navigation.Screen
import com.nokhbativi.ui.navigation.screens
import com.nokhbativi.ui.screen.HomeScreen
import com.nokhbativi.ui.screen.ListChannels
import com.nokhbativi.ui.screen.LiveEvents
import com.nokhbativi.ui.screen.TiViScreen
import com.nokhbativi.ui.theme.TiViTheme
import com.nokhbativi.worker.FirestoreDataWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mvm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val request: WorkRequest = OneTimeWorkRequestBuilder<FirestoreDataWorker>().build()
        WorkManager.getInstance(applicationContext).enqueue(request)

        setContent {
            TiViTheme {
                TiViApp(mainViewModel = mvm)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TiViApp(mainViewModel: MainViewModel) {

    var mCode by rememberSaveable { mutableStateOf("") }
    var mName by rememberSaveable { mutableStateOf("") }

    val countries by mainViewModel.categories("COU").collectAsState(initial = listOf())

    val events by mainViewModel.liveEvents.collectAsState(initial = listOf())

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    val sheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    BackHandler(sheetState.isVisible) { coroutineScope.launch { sheetState.hide() } }

    ModalBottomSheetLayout(sheetState = sheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetContent = {
            TopAppBar(
                title = {
                    Text(
                        text = mName,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                        ),
                        onClick = {
                            coroutineScope.launch { sheetState.hide() }
                        },
                    ) {
                        Icon(Icons.Rounded.Close, null)
                    }
                },
            )
            ListChannels(code = mCode, mainViewModel = mainViewModel)
        }) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            contentColor = MaterialTheme.colorScheme.surface,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
//                        navigationIcon = {
//                            Icon(
//                                painter = painterResource(id = R.drawable.banner_logo),
//                                contentDescription = null
//                            )
//                        },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            bottomBar = {
                NavBar(navController = navController)
            }) { paddingValues ->
            Column(Modifier.padding(paddingValues = paddingValues)) {

                NavHost(
                    navController = navController, startDestination = Screen.Home.route
                ) {
                    screens.forEach { screen ->
                        when (screen.route) {
                            Screen.Home.route -> {
                                composable(route = screen.route) {
                                    HomeScreen()
                                }
                            }

                            Screen.Categories.route -> {
                                composable(route = screen.route) {
                                    TiViScreen(countries = countries) { name, code ->
                                        mName = name
                                        mCode = code
                                        coroutineScope.launch {
                                            delay(50)
                                            sheetState.show()
                                        }
                                    }
                                }
                            }

                            Screen.Account.route -> {
                                composable(route = screen.route) {
                                    LiveEvents(events = events)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}