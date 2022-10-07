package com.nokhbativi.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.nokhbativi.model.database.DatabaseLiveEvent
import com.nokhbativi.network.SoccerBaseUrl
import com.nokhbativi.ui.main.MainViewModel
import com.nokhbativi.ui.single.CoilImage
import com.nokhbativi.util.IMAGE
import com.nokhbativi.util.TEAM
import com.nokhbativi.util.commit
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LiveEvents(events: List<DatabaseLiveEvent>) {

    val leagues = events.groupBy { it.tournament }

    val viewModel: MainViewModel = viewModel()
//    val isRefreshing by viewModel.isRefreshing.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(false),
        onRefresh = {
            viewModel.viewModelScope.launch {
                viewModel.repository.refreshLiveEvents()
            }
        },
    ) {

        if (events.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                leagues.forEach { (tournament, events) ->
                    stickyHeader {
                        SingleLeagueHeader(tournament = tournament)
                    }
                    items(events) { event ->
                        SingleEvent(event = event)
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "No Match...",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun SingleEvent(event: DatabaseLiveEvent) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .height(72.dp)
            .clickable {

            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {

            val (homeName, homeLogo, homeScore, spacer, awayScore, awayLogo, awayName) = createRefs()
            TeamTitle(
                modifier = Modifier.constrainAs(homeName) {
                    top.linkTo(homeLogo.top)
                    end.linkTo(homeLogo.start)
                    bottom.linkTo(homeLogo.bottom)
                },
                name = event.homeTeam.shortName
            )
            CoilImage(data = SoccerBaseUrl.plus(TEAM).plus(event.homeTeam.id).plus(IMAGE),
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp)
                    .constrainAs(homeLogo) {
                        top.linkTo(homeScore.top)
                        end.linkTo(homeScore.start)
                        bottom.linkTo(homeScore.bottom)
                    })
            Text(
                modifier = Modifier.constrainAs(homeScore) {
                    top.linkTo(spacer.top)
                    end.linkTo(spacer.start)
                    bottom.linkTo(spacer.bottom)
                }, text = event.homeScore.display.commit()
            )
            Spacer(modifier = Modifier
                .width(48.dp)
                .fillMaxHeight()
                .constrainAs(spacer) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                })
            Text(
                modifier = Modifier.constrainAs(awayScore) {
                    start.linkTo(spacer.end)
                    top.linkTo(spacer.top)
                    bottom.linkTo(spacer.bottom)
                }, text = event.awayScore.display.commit()
            )
            CoilImage(
                data = SoccerBaseUrl.plus(TEAM)
                    .plus(event.awayTeam.id)
                    .plus(IMAGE),
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp)
                    .constrainAs(awayLogo) {
                        start.linkTo(awayScore.end)
                        top.linkTo(awayScore.top)
                        bottom.linkTo(awayScore.bottom)
                    },
            )
            TeamTitle(
                modifier = Modifier.constrainAs(awayName) {
                    start.linkTo(awayLogo.end)
                    top.linkTo(awayLogo.top)
                    bottom.linkTo(awayLogo.bottom)
                },
                name = event.awayTeam.shortName
            )
        }
    }
}

@Composable
fun TeamTitle(modifier: Modifier, name: String) {
    Text(
        modifier = modifier,
        text = name,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 2,
        softWrap = true
    )
}