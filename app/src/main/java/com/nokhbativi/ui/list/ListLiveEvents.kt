package com.nokhbativi.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.nokhbativi.ui.main.MainViewModel
import com.nokhbativi.ui.screen.SingleLeagueHeader
import com.nokhbativi.ui.single.Image
import com.nokhbativi.ui.single.SingleEvent
import com.nokhbativi.util.imgur
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListLiveEvents() {

    val mainViewModel: MainViewModel = hiltViewModel()

    val events by mainViewModel.liveEvents.collectAsState(initial = listOf())

    val leagues = events.groupBy { it.tournament }

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(2000)
            refreshing = false
        }
    }

    SwipeRefresh(
        modifier = Modifier.fillMaxSize(),
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = {
            refreshing = true
            mainViewModel.viewModelScope.launch {
                mainViewModel.repository.refreshLiveEvents()
            }
        },
    ) {

        if (events.isNotEmpty()) {
            LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
                leagues.forEach { (tournament, events) ->
                    stickyHeader {
                        SingleLeagueHeader(
                            tournament = tournament,
                            modifier = Modifier.placeholder(
                                color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.scrim else Color.Gray,
                                visible = refreshing,
                                highlight = PlaceholderHighlight.shimmer(),
                            )
                        )
                    }
                    items(events) { event ->
                        SingleEvent(
                            event = event,
                            modifier = Modifier.placeholder(
                                color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.scrim else Color.Gray,
                                visible = refreshing,
                                highlight = PlaceholderHighlight.shimmer(),
                            )
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.size(240.dp),
                    data = "PtGYKvO".imgur()
                )
            }
        }
    }
}