package ma.nokhbativi.model.network

data class NetworkLiveEvent(
    val id: Int, // 10433883
    val coverage: Int?, // -1
    val winnerCode: Int?, // 0
    val awayRedCards: Int?, // 2
    val homeRedCards: Int?, // 2
    val startTimestamp: Int, // 1664816400
    val lastPeriod: String?, // period2
    val time: Time,
    val status: Status,
    val homeTeam: HomeTeam,
    val awayTeam: AwayTeam,
    val homeScore: HomeScore,
    val awayScore: AwayScore,
    val roundInfo: RoundInfo?,
    val tournament: Tournament,
    val statusTime: StatusTime?,
    val finalResultOnly: Boolean, // false
    val hasGlobalHighlights: Boolean, // false
    val hasEventPlayerHeatMap: Boolean?, // true
    val hasEventPlayerStatistics: Boolean?, // true
) {
    data class Tournament(
        val id: Int, // 62
        val name: String, // Super Lig
        val priority: Int, // 310
        val category: Category,
        val uniqueTournament: UniqueTournament?,
    ) {
        data class Category(
            val id: Int, // 46
            val name: String, // Turkey
            val flag: String, // turkey
            val alpha2: String // TR
        )

        data class UniqueTournament(
            val id: Int, // 52
            val name: String, // Süper Lig
            val userCount: Int?, // 86955
            val category: UniqueCategory?,
            val hasPositionGraph: Boolean, // true
            val hasEventPlayerStatistics: Boolean, // true
            val displayInverseHomeAwayTeams: Boolean // false
        ) {
            data class UniqueCategory(
                val id: Int, // 46
                val name: String, // Turkey
                val flag: String, // turkey
                val alpha2: String // TR
            )
        }
    }

    data class RoundInfo(
        val round: Int // 8
    )

    data class Status(
        val code: Int, // 31
        val type: String, // inprogress
        val description: String, // Halftime
    )

    data class HomeTeam(
        val id: Int, // 4954
        val name: String, // Fatih Karagümrük
        val nameCode: String, // KAR
        val shortName: String, // Karagümrük
        val type: Int, // 0
        val userCount: Int, // 7764
        val disabled: Boolean?, // false
        val national: Boolean, // false
    )

    data class AwayTeam(
        val id: Int, // 4954
        val name: String, // Fatih Karagümrük
        val nameCode: String, // KAR
        val shortName: String, // Karagümrük
        val type: Int, // 0
        val userCount: Int, // 7764
        val disabled: Boolean?, // false
        val national: Boolean, // false
    )

    data class HomeScore(
        val current: Int, // 1
        val display: Int, // 1
        val period1: Int?, // 1
        val normaltime: Int? // 0
    )

    data class AwayScore(
        val current: Int, // 0
        val display: Int, // 0
        val period1: Int?, // 0
        val normaltime: Int? // 1
    )

    data class Time(
        val max: Int?, // 5400
        val extra: Int?, // 540
        val initial: Int?, // 2700
        val injuryTime1: Int?, // 3
        val currentPeriodStartTimestamp: Int, // 1664819602
    )

    data class StatusTime(
        val prefix: String,
        val max: Int, // 5400
        val extra: Int, // 540
        val initial: Int, // 2700
        val timestamp: Int, // 1664820210
    )
}