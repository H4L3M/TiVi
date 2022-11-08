@file:OptIn(ExperimentalMaterial3Api::class)

package com.nokhbativi.ui.single

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nokhbativi.model.database.DatabaseFeaturedEvent
import com.nokhbativi.data.network.SoccerBaseUrl
import com.nokhbativi.util.IMAGE
import com.nokhbativi.util.TEAM
import com.nokhbativi.util.commit


@Composable
fun SingleFeaturedEvent(featuredEvent: DatabaseFeaturedEvent, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = {}
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            val (homeName, homeLogo, homeScore, spacer, awayScore, awayLogo, awayName) = createRefs()
            Text(
                modifier = modifier
                    .padding(8.dp)
                    .constrainAs(homeName) {
                        start.linkTo(homeLogo.start)
                        top.linkTo(homeLogo.bottom)
                        end.linkTo(homeLogo.end)
                    },
                text = featuredEvent.homeTeam.shortName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(Modifier.width(16.dp))
            Image(
                modifier = modifier
                    .size(64.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(homeLogo) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(homeScore.start)
                        bottom.linkTo(parent.bottom)
                    },
                data = SoccerBaseUrl.plus(TEAM).plus(featuredEvent.homeTeam.id).plus(IMAGE),
            )
            Spacer(Modifier.width(16.dp))
            Text(
                modifier = Modifier.constrainAs(homeScore) {
                    top.linkTo(spacer.top)
                    end.linkTo(spacer.start)
                    bottom.linkTo(spacer.bottom)
                },
                text = featuredEvent.homeScore.display.commit(),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
            Spacer(modifier = Modifier
                .width(16.dp)
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
                },
                text = featuredEvent.awayScore.display.commit(),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
            Spacer(Modifier.width(16.dp))
            Image(
                modifier = modifier
                    .size(64.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(awayLogo) {
                        start.linkTo(awayScore.end)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                data = SoccerBaseUrl.plus(TEAM).plus(featuredEvent.awayTeam.id).plus(IMAGE),
            )
            Spacer(Modifier.width(16.dp))
            Text(
                modifier = modifier
                    .padding(8.dp)
                    .constrainAs(awayName) {
                        start.linkTo(awayLogo.start)
                        top.linkTo(awayLogo.bottom)
                        end.linkTo(awayLogo.end)
                    },
                text = featuredEvent.awayTeam.shortName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}