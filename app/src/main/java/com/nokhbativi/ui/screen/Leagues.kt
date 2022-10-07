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
import com.nokhbativi.model.database.DatabaseLiveEvent.Tournament
import com.nokhbativi.ui.single.CoilImage
import com.nokhbativi.util.IMGUR
import com.nokhbativi.util.PLACEHOLDER
import com.nokhbativi.util.leagueLogo


@Composable
fun SingleLeagueHeader(tournament: Tournament) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        CoilImage(
            data = tournament.uniqueTournament?.id?.leagueLogo() ?: IMGUR.plus(PLACEHOLDER),
            modifier = Modifier
                .size(48.dp)
                .padding(4.dp)
        )
        Text(
            text = tournament.name,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}