@file:OptIn(ExperimentalMaterial3Api::class)

package com.nokhbativi.ui.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.model.database.DatabaseCountry
import com.nokhbativi.network.BaseUrl

@Composable
fun SingleCountry(country: DatabaseCountry, onClick: (name: String, code: String) -> Unit) {

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(4.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 0.dp
        ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = { onClick(name(country) ,country.code.alpha2) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

//            ConstraintLayout(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                val (logo, name) = createRefs()
            CoilImage(
                modifier = Modifier
                    .size(72.dp)
                    .padding(top = 4.dp)
                    .clip(CircleShape),
//                        .constrainAs(logo) {
//                            start.linkTo(parent.start)
//                            top.linkTo(parent.top)
//                            end.linkTo(parent.end)
//                        },
                data = BaseUrl.plus("flag/").plus(country.code.alpha2.lowercase().plus(".png"))
            )
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 4.dp),
//                        .padding(bottom = 4.dp),
//                        .constrainAs(name) {
//                            start.linkTo(logo.start)
//                            top.linkTo(logo.bottom)
//                            end.linkTo(logo.end)
//                        },
                textAlign = TextAlign.Center,
                text = name(country)
            )
//            }
        }
    }
}

@Composable
fun SingleChannel(channel: DatabaseChannel, onClick: (stream: String?) -> Unit) {

    Card(
        modifier = Modifier
            .padding(2.dp)
            .size(56.dp),
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
                    .clip(CircleShape)
                    .padding(8.dp)
                    .constrainAs(logo) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                data = channel.logo
            )
            Text(
                modifier = Modifier
//                    .fillMaxWidth()
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

private fun name(country: DatabaseCountry): String =
    when (Locale.current.language) {
        "ar" -> country.name.ar
        "bg" -> country.name.bg
        "cs" -> country.name.cs
        "da" -> country.name.da
        "de" -> country.name.de
        "el" -> country.name.el
        "en" -> country.name.en
        "eo" -> country.name.eo
        "es" -> country.name.es
        "et" -> country.name.et
        "eu" -> country.name.eu
        "fi" -> country.name.fi
        "fr" -> country.name.fr
        "hu" -> country.name.hu
        "hy" -> country.name.hy
        "it" -> country.name.it
        "ja" -> country.name.ja
        "ko" -> country.name.ko
        "lt" -> country.name.lt
        "nl" -> country.name.nl
        "no" -> country.name.no
        "pl" -> country.name.pl
        "pt" -> country.name.pt
        "ro" -> country.name.ro
        "ru" -> country.name.ru
        "sk" -> country.name.sk
        "sv" -> country.name.sv
        "th" -> country.name.th
        "uk" -> country.name.uk
        "zh" -> country.name.zh
        else -> country.name.en
    }