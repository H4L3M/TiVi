@file:OptIn(ExperimentalMaterial3Api::class)

package ma.nokhbativi.ui.single

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import ma.nokhbativi.model.database.DatabaseLiveEvent
import ma.nokhbativi.data.network.SoccerBaseUrl
import ma.nokhbativi.util.IMAGE
import ma.nokhbativi.util.TEAM
import ma.nokhbativi.util.commit


@Composable
fun SingleEvent(event: DatabaseLiveEvent, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .fillMaxWidth()
            .height(64.dp)
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
            modifier = Modifier.fillMaxWidth()
        ) {

            val (homeName, homeLogo, homeScore, spacer, awayScore, awayLogo, awayName) = createRefs()
            TeamTitle(
                modifier = modifier.constrainAs(homeName) {
                    top.linkTo(homeLogo.top)
                    end.linkTo(homeLogo.start)
                    bottom.linkTo(homeLogo.bottom)
                },
                name = event.homeTeam.shortName
            )
            Spacer(Modifier.width(16.dp))
            Image(
                modifier = modifier
                    .size(56.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(homeLogo) {
                        top.linkTo(homeScore.top)
                        end.linkTo(homeScore.start)
                        bottom.linkTo(homeScore.bottom)
                    },
                data = SoccerBaseUrl.plus(TEAM).plus(event.homeTeam.id).plus(IMAGE),
            )
            Spacer(Modifier.width(16.dp))
            Text(
                modifier = Modifier.constrainAs(homeScore) {
                    top.linkTo(spacer.top)
                    end.linkTo(spacer.start)
                    bottom.linkTo(spacer.bottom)
                }, text = event.homeScore.display.commit()
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
                }, text = event.awayScore.display.commit()
            )
            Spacer(Modifier.width(16.dp))
            Image(
                modifier = modifier
                    .size(56.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(awayLogo) {
                        start.linkTo(awayScore.end)
                        top.linkTo(awayScore.top)
                        bottom.linkTo(awayScore.bottom)
                    },
                data = SoccerBaseUrl.plus(TEAM).plus(event.awayTeam.id).plus(IMAGE),
            )
            Spacer(Modifier.width(16.dp))
            TeamTitle(
                modifier = modifier.constrainAs(awayName) {
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