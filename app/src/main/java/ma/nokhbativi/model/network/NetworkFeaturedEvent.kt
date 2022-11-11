package ma.nokhbativi.model.network


data class NetworkFeaturedEvent(
    val id: Int, // 10664824
    val homeTeam: HomeTeam,
    val awayTeam: AwayTeam,
    val homeScore: HomeScore,
    val awayScore: AwayScore,
) {
    data class HomeTeam(
        val id: Int, // 1968
        val name: String, // Santos
        val shortName: String, // Santos
        val nameCode: String, // SAN
    )

    data class AwayTeam(
        val id: Int, // 1980
        val name: String, // Juventude
        val shortName: String, // Juventude
        val nameCode: String, // JUV
    )

    data class HomeScore(
        val display: Int // 1
    )

    data class AwayScore(
        val display: Int // 0
    )
}
