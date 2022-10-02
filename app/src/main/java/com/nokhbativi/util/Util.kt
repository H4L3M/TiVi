package com.nokhbativi.util

import com.nokhbativi.network.BaseUrl

private const val FLAG = "https://www.sofascore.com/static/images/flags/"

fun Int?.commit() = if (this.toString() == "null") "-" else this.toString()

fun Int.logo(): String = BaseUrl.plus(TEAM).plus(this).plus(IMAGE)

fun Int.leagueLogo(): String = BaseUrl.plus(LEAGUE).plus(this).plus(IMAGE)

fun String.flag(): String = FLAG.plus(this.lowercase()).plus(".png")