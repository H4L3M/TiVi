@file:OptIn(ExperimentalMaterial3Api::class)

package com.nokhbativi.ui.single

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.util.imgur

@Composable
fun SingleChannelHorizontal(channel: DatabaseChannel, onClick: (stream: String?) -> Unit) {
    Card(
        modifier = Modifier
            .padding(2.dp)
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
        onClick = {
            onClick(channel.stream)
        }
    ) {
        ConstraintLayout {
            val (logo, name) = createRefs()
            CoilImage(
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
//                    .clip(RoundedCornerShape(8.dp))
                    .clip(CircleShape)
                    .constrainAs(logo) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                data = if(channel.logo.length == 7) channel.logo.imgur() else channel.logo
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .constrainAs(name) {
                        start.linkTo(logo.end)
                        top.linkTo(logo.top)
                        bottom.linkTo(logo.bottom)
                    },
                textAlign = TextAlign.Center,
                text = channel.name.en!!
            )
        }
    }
}