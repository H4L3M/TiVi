package com.nokhbativi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ma.nokhbativi.model.database.DatabaseLiveEvent.Tournament
import ma.nokhbativi.ui.single.Image
import ma.nokhbativi.util.IMGUR
import ma.nokhbativi.util.PLACEHOLDER
import ma.nokhbativi.util.leagueLogo


@Composable
fun SingleLeagueHeader(tournament: Tournament, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            data = tournament.uniqueTournament?.id?.leagueLogo() ?: IMGUR.plus(PLACEHOLDER),
            modifier = modifier
                .size(48.dp)
                .padding(4.dp)
        )
        Text(
            modifier = modifier,
            text = tournament.name,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}