package com.nokhbativi.util

import com.nokhbativi.data.network.SoccerBaseUrl
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

private const val FLAG = "https://www.sofascore.com/static/images/flags/"

fun Int?.commit() = if (this.toString() == "null") "-" else this.toString()

fun Int.logo(): String = SoccerBaseUrl.plus(TEAM).plus(this).plus(IMAGE)

fun Int.leagueLogo(): String = SoccerBaseUrl.plus(LEAGUE).plus(this).plus(IMAGE)

fun String.flag(): String = COUNTRY_FLAG.plus(this.lowercase()).plus(".png")

fun String.imgur(): String = IMGUR.plus(this.plus(".png"))

val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
