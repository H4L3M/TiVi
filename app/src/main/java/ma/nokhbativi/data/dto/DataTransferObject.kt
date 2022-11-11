package ma.nokhbativi.data.dto

import ma.nokhbativi.data.container.NetworkFeaturedEventsContainer
import ma.nokhbativi.data.container.NetworkLiveEventsContainer
import ma.nokhbativi.model.database.DatabaseCategory
import ma.nokhbativi.model.database.DatabaseChannel
import ma.nokhbativi.model.database.DatabaseFeaturedEvent
import ma.nokhbativi.model.database.DatabaseLiveEvent
import ma.nokhbativi.model.network.FirestoreCategory
import ma.nokhbativi.model.network.FirestoreChannel

fun NetworkLiveEventsContainer.asDatabaseModel(): Array<DatabaseLiveEvent> {
    return events.map {
        DatabaseLiveEvent(
            id = it.id,
            coverage = it.coverage,
            winnerCode = it.winnerCode,
            awayRedCards = it.awayRedCards,
            homeRedCards = it.homeRedCards,
            startTimestamp = it.startTimestamp,
            lastPeriod = it.lastPeriod,
            time = DatabaseLiveEvent.Time(
                max = it.time.max,
                extra = it.time.extra,
                initial = it.time.initial,
                injuryTime1 = it.time.injuryTime1,
                currentPeriodStartTimestamp = it.time.currentPeriodStartTimestamp,
            ),
            status = DatabaseLiveEvent.Status(
                code = it.status.code,
                type = it.status.type,
                description = it.status.description,
            ),
            homeTeam = DatabaseLiveEvent.HomeTeam(
                id = it.homeTeam.id,
                name = it.homeTeam.name,
                nameCode = it.homeTeam.nameCode,
                shortName = it.homeTeam.shortName,
                type = it.homeTeam.type,
                userCount = it.homeTeam.userCount,
                disabled = it.homeTeam.disabled,
                national = it.homeTeam.national
            ),
            awayTeam = DatabaseLiveEvent.AwayTeam(
                id = it.awayTeam.id,
                name = it.awayTeam.name,
                nameCode = it.awayTeam.nameCode,
                shortName = it.awayTeam.shortName,
                type = it.awayTeam.type,
                userCount = it.awayTeam.userCount,
                disabled = it.awayTeam.disabled,
                national = it.awayTeam.national
            ),
            homeScore = DatabaseLiveEvent.HomeScore(
                current = it.homeScore.current,
                display = it.homeScore.display,
                period1 = it.homeScore.period1,
                normaltime = it.homeScore.normaltime
            ),
            awayScore = DatabaseLiveEvent.AwayScore(
                current = it.awayScore.current,
                display = it.awayScore.display,
                period1 = it.awayScore.period1,
                normaltime = it.awayScore.normaltime
            ),
            roundInfo = DatabaseLiveEvent.RoundInfo(round = it.roundInfo?.round),
            tournament = DatabaseLiveEvent.Tournament(
                id = it.tournament.id,
                name = it.tournament.name,
                priority = it.tournament.priority,
//                category = DatabaseLiveEvent.Tournament.Category(
//                    id = it.tournament.category.id,
//                    name = it.tournament.category.name,
//                    flag = it.tournament.category.flag,
//                    alpha2 = it.tournament.category.alpha2,
//                ),
                uniqueTournament = DatabaseLiveEvent.Tournament.UniqueTournament(
                    id = it.tournament.uniqueTournament?.id,
                    name = it.tournament.uniqueTournament?.name,
                    userCount = it.tournament.uniqueTournament?.userCount,
                    hasPositionGraph = it.tournament.uniqueTournament?.hasPositionGraph ?: false,
                    hasEventPlayerStatistics = it.tournament.uniqueTournament?.hasEventPlayerStatistics
                        ?: false,
                    displayInverseHomeAwayTeams = it.tournament.uniqueTournament?.displayInverseHomeAwayTeams
                        ?: false
                ),
            ),
            statusTime = null,
            finalResultOnly = it.finalResultOnly,
            hasGlobalHighlights = it.hasGlobalHighlights,
            hasEventPlayerHeatMap = it.hasEventPlayerHeatMap,
            hasEventPlayerStatistics = it.hasEventPlayerStatistics
        )
    }.toTypedArray()
}

fun NetworkFeaturedEventsContainer.asDatabaseModel(): Array<DatabaseFeaturedEvent> {
    return featuredEvents.map {
        DatabaseFeaturedEvent(
            id = it.id,
            homeTeam = DatabaseFeaturedEvent.HomeTeam(
                id = it.homeTeam.id,
                name = it.homeTeam.name,
                nameCode = it.homeTeam.nameCode,
                shortName = it.homeTeam.shortName,
            ),
            awayTeam = DatabaseFeaturedEvent.AwayTeam(
                id = it.awayTeam.id,
                name = it.awayTeam.name,
                nameCode = it.awayTeam.nameCode,
                shortName = it.awayTeam.shortName,
            ),
            homeScore = DatabaseFeaturedEvent.HomeScore(
                display = it.homeScore.current,
            ),
            awayScore = DatabaseFeaturedEvent.AwayScore(
                display = it.awayScore.current,
            )
        )
    }.toTypedArray()
}

fun FirestoreCategory.asDatabaseModel(): DatabaseCategory {
    return DatabaseCategory(
        id = this.id ?: 0,
        logo = this.logo ?: "",
        code = DatabaseCategory.Code(
            alpha = this.code?.alpha.toString(),
            type = this.code?.type.toString()
        ),
        name = DatabaseCategory.Name(ar = this.name?.ar ?: "", en = this.name?.en ?: ""),
        priority = this.priority ?: 0,
        visible = this.visible ?: false
    )
}

fun FirestoreChannel.asDatabaseModel(): DatabaseChannel {
    return DatabaseChannel(
        id = this.id ?: 0,
        name = DatabaseChannel.Name(ar = this.name?.ar ?: "", en = this.name?.en ?: ""),
        logo = this.logo ?: "",
        code = DatabaseChannel.Code(
            country = this.code?.country.toString(),
            pack = this.code?.pack.toString(),
            category = this.code?.category.toString()
        ),
        stream = this.stream.toString(),
        priority = this.priority ?: 0,
        userAgent = this.userAgent.toString(),
        visible = this.visible ?: false
    )
}