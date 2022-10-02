package com.nokhbativi.ui.single

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nokhbativi.model.database.DatabaseEvent
import com.nokhbativi.network.BaseUrl
import com.nokhbativi.util.IMAGE
import com.nokhbativi.util.TEAM
import com.nokhbativi.util.commit

@Composable
fun SingleEvent(event: DatabaseEvent?) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .fillMaxWidth()
            .height(72.dp)
            .clickable {

            }
            .focusable(enabled = true),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            focusedElevation = 4.dp, hoveredElevation = 2.dp
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
                }, name = event!!.homeTeam.shortName
            )
            CoilImage(data = BaseUrl.plus(TEAM).plus(event.homeTeam.id).plus(IMAGE),
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
                }, text = event.homeTeam.homeScore?.display.commit()
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
                }, text = event.awayTeam.awayScore?.display.commit()
            )
            CoilImage(
                data = BaseUrl.plus(TEAM)
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
                }, name = event.awayTeam.shortName
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
@Preview
@Composable
fun SingleEventPrev() {
    SingleEvent(null)
}