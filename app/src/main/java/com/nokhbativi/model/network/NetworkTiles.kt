package com.nokhbativi.model.network


data class NetworkBuzzerTile(
    val event: Event? = null,
    val player: Player? = null,
    val isHome: Boolean? = null, // true
    val type: Int? = null, // 6
    val action: Int? = null, // 12
    val actionValue: String? = null, // 10729262-1142088
    val reason: String? = null, // topBasketballEventPlayerPerformance
    val createdBy: Int? = null, // 2
    val reasonSuborder: Int? = null, // -74800
    val visible: Boolean? = null, // true
    val id: Int? = null, // 25652
    val topStatistics: TopStatistics? = null,
    val interestingStatistic: InterestingStatistic? = null,
    val transfer: Transfer? = null,
    val visibleToTimestamp: Int? = null, // 1665854602
    val rating: Double? = null, // 8.5
    val text: String? = null, // Check out new features
    val label: String? = null, // WHAT'S NEW
    val labelBackground: String? = null, // #da9403
    val imageUrl: String? = null, // https://www.sofascore.com/i/c0e685bbf6e5ad498aba0bb9b73b3df7
    val overlay: Int? = null, // 1
    val position: Int? = null, // 1999
    val countries: List<String?>? = null,
    val visibleFromTimestamp: Int? = null // 1665065700
) {
    data class Event(
        val tournament: Tournament? = null,
        val roundInfo: RoundInfo? = null,
        val customId: String? = null, // UbcbsBccb
        val status: Status? = null,
        val winnerCode: Int? = null, // 1
        val homeTeam: HomeTeam? = null,
        val awayTeam: AwayTeam? = null,
        val homeScore: HomeScore? = null,
        val awayScore: AwayScore? = null,
        val time: Time? = null,
        val changes: Changes? = null,
        val hasGlobalHighlights: Boolean? = null, // false
        val hasEventPlayerStatistics: Boolean? = null, // true
        val id: Int? = null, // 10729262
        val periods: Periods? = null,
        val startTimestamp: Int? = null, // 1665574500
        val slug: String? = null, // shanxi-loongs-sichuan-blue-whales
        val finalResultOnly: Boolean? = null, // false
        val hasEventPlayerHeatMap: Boolean? = null, // true
        val statusTime: StatusTime? = null,
        val lastPeriod: String? = null // period2
    ) {
        data class Tournament(
            val name: String? = null, // CBA
            val slug: String? = null, // cba
            val category: Category? = null,
            val uniqueTournament: UniqueTournament? = null,
            val priority: Int? = null, // 0
            val id: Int? = null // 10233
        ) {
            data class Category(
                val name: String? = null, // China
                val slug: String? = null, // china
                val sport: Sport? = null,
                val id: Int? = null, // 548
                val flag: String? = null, // china
                val alpha2: String? = null // CN
            ) {
                data class Sport(
                    val name: String? = null, // Basketball
                    val slug: String? = null, // basketball
                    val id: Int? = null // 2
                )
            }

            data class UniqueTournament(
                val name: String? = null, // CBA
                val slug: String? = null, // cba
                val category: Category? = null,
                val userCount: Int? = null, // 2836
                val id: Int? = null, // 1566
                val hasEventPlayerStatistics: Boolean? = null, // false
                val hasPositionGraph: Boolean? = null, // false
                val displayInverseHomeAwayTeams: Boolean? = null, // false
                val hasBoxScore: Boolean? = null // true
            ) {
                data class Category(
                    val name: String? = null, // China
                    val slug: String? = null, // china
                    val sport: Sport? = null,
                    val id: Int? = null, // 548
                    val flag: String? = null, // china
                    val alpha2: String? = null // CN
                ) {
                    data class Sport(
                        val name: String? = null, // Basketball
                        val slug: String? = null, // basketball
                        val id: Int? = null // 2
                    )
                }
            }
        }

        data class RoundInfo(
            val round: Int? = null, // 1
            val name: String? = null, // Final
            val slug: String? = null, // final
            val cupRoundType: Int? = null // 1
        )

        data class Status(
            val code: Int? = null, // 100
            val description: String? = null, // Ended
            val type: String? = null // finished
        )

        data class HomeTeam(
            val name: String? = null, // Sichuan Blue Whales
            val slug: String? = null, // sichuan-blue-whales
            val shortName: String? = null, // Sichuan Blue Whales
            val gender: String? = null, // M
            val sport: Sport? = null,
            val userCount: Int? = null, // 339
            val nameCode: String? = null, // SBW
            val national: Boolean? = null, // false
            val type: Int? = null, // 0
            val id: Int? = null, // 130094
            val country: Country? = null,
            val subTeams: List<Any?>? = null,
            val teamColors: TeamColors? = null,
            val disabled: Boolean? = null // false
        ) {
            data class Sport(
                val name: String? = null, // Basketball
                val slug: String? = null, // basketball
                val id: Int? = null // 2
            )

            data class Country(
                val alpha2: String? = null, // CN
                val name: String? = null // China
            )

            data class TeamColors(
                val primary: String? = null, // #201690
                val secondary: String? = null, // #ff6600
                val text: String? = null // #ff6600
            )
        }

        data class AwayTeam(
            val name: String? = null, // Shanxi Loongs
            val slug: String? = null, // shanxi-loongs
            val shortName: String? = null, // Shanxi Loongs
            val gender: String? = null, // M
            val sport: Sport? = null,
            val userCount: Int? = null, // 507
            val nameCode: String? = null, // SHA
            val national: Boolean? = null, // false
            val type: Int? = null, // 0
            val id: Int? = null, // 130126
            val country: Country? = null,
            val subTeams: List<Any?>? = null,
            val teamColors: TeamColors? = null,
            val disabled: Boolean? = null // false
        ) {
            data class Sport(
                val name: String? = null, // Basketball
                val slug: String? = null, // basketball
                val id: Int? = null // 2
            )

            data class Country(
                val alpha2: String? = null, // CN
                val name: String? = null // China
            )

            data class TeamColors(
                val primary: String? = null, // #ffad33
                val secondary: String? = null, // #000000
                val text: String? = null // #000000
            )
        }

        data class HomeScore(
            val current: Int? = null, // 108
            val display: Int? = null, // 108
            val period1: Int? = null, // 21
            val period2: Int? = null, // 33
            val period3: Int? = null, // 28
            val period4: Int? = null, // 26
            val normaltime: Int? = null // 108
        )

        data class AwayScore(
            val current: Int? = null, // 103
            val display: Int? = null, // 103
            val period1: Int? = null, // 29
            val period2: Int? = null, // 23
            val period3: Int? = null, // 30
            val period4: Int? = null, // 21
            val normaltime: Int? = null // 103
        )

        data class Time(
            val played: Int? = null, // 2880
            val periodLength: Int? = null, // 720
            val overtimeLength: Int? = null, // 300
            val totalPeriodCount: Int? = null, // 4
            val currentPeriodStartTimestamp: Int? = null, // 1665582483
            val injuryTime1: Int? = null, // 2
            val initial: Int? = null, // 2700
            val max: Int? = null, // 5400
            val extra: Int? = null, // 540
            val injuryTime2: Int? = null // 5
        )

        data class Changes(
            val changes: List<String?>? = null,
            val changeTimestamp: Int? = null // 1665582488
        )

        data class Periods(
            val current: String? = null, // Score
            val period1: String? = null, // 1st quarter
            val period2: String? = null, // 2nd quarter
            val period3: String? = null, // 3rd quarter
            val period4: String? = null, // 4th quarter
            val overtime: String? = null, // Overtime
            val penalties: String? = null // Penalty
        )

        data class StatusTime(
            val prefix: String? = null,
            val initial: Int? = null, // 2700
            val max: Int? = null, // 5400
            val timestamp: Int? = null, // 1665596916
            val extra: Int? = null // 540
        )
    }

    data class Player(
        val name: String? = null, // Marlain Veal
        val firstName: String? = null, // Marlain
        val lastName: String? = null, // Veal
        val slug: String? = null, // veal-marlain
        val shortName: String? = null, // M. Veal
        val position: String? = null, // G
        val userCount: Int? = null, // 0
        val id: Int? = null, // 1142088
        val country: Country? = null
    ) {
        data class Country(
            val alpha2: String? = null, // US
            val name: String? = null // USA
        )
    }

    data class TopStatistics(
        val points: Int? = null, // 37
        val rebounds: Int? = null, // 11
        val assists: Int? = null // 5
    )

    data class InterestingStatistic(
        val key: String? = null, // steals
        val name: String? = null, // Steals
        val value: Int? = null // 6
    )

    data class Transfer(
        val player: Player? = null,
        val transferFrom: TransferFrom? = null,
        val transferTo: TransferTo? = null,
        val fromTeamName: String? = null, // No team
        val toTeamName: String? = null, // AC Ajaccio
        val type: Int? = null, // 3
        val transferFee: Int? = null, // 0
        val transferFeeDescription: String? = null, // -
        val id: Int? = null, // 1499508
        val transferDateTimestamp: Int? = null, // 1665532800
        val transferFeeRaw: TransferFeeRaw? = null
    ) {
        data class Player(
            val name: String? = null, // Youssef Belaïli
            val firstName: String? = null,
            val lastName: String? = null,
            val slug: String? = null, // youssef-belaili
            val shortName: String? = null, // Y. Belaïli
            val position: String? = null, // M
            val userCount: Int? = null, // 11161
            val id: Int? = null, // 901827
            val country: Country? = null
        ) {
            data class Country(
                val alpha2: String? = null, // DZ
                val name: String? = null // Algeria
            )
        }

        data class TransferFrom(
            val name: String? = null, // No team
            val slug: String? = null, // no-team
            val shortName: String? = null, // No team
            val sport: Sport? = null,
            val userCount: Int? = null, // 20
            val nameCode: String? = null, // NTE
            val disabled: Boolean? = null, // true
            val national: Boolean? = null, // false
            val type: Int? = null, // 0
            val id: Int? = null, // 241802
            val country: Country? = null,
            val subTeams: List<Any?>? = null,
            val teamColors: TeamColors? = null,
            val gender: String? = null // M
        ) {
            data class Sport(
                val name: String? = null, // Football
                val slug: String? = null, // football
                val id: Int? = null // 1
            )

            data class Country(
                val alpha2: String? = null, // ES
                val name: String? = null // Spain
            )

            data class TeamColors(
                val primary: String? = null, // #d0d0d0
                val secondary: String? = null, // #000000
                val text: String? = null // #000000
            )
        }

        data class TransferTo(
            val name: String? = null, // AC Ajaccio
            val slug: String? = null, // ac-ajaccio
            val shortName: String? = null, // Ajaccio
            val gender: String? = null, // M
            val sport: Sport? = null,
            val userCount: Int? = null, // 14382
            val nameCode: String? = null, // ACA
            val national: Boolean? = null, // false
            val type: Int? = null, // 0
            val id: Int? = null, // 1660
            val country: Country? = null,
            val subTeams: List<Any?>? = null,
            val teamColors: TeamColors? = null
        ) {
            data class Sport(
                val name: String? = null, // Football
                val slug: String? = null, // football
                val id: Int? = null // 1
            )

            data class Country(
                val alpha2: String? = null, // FR
                val name: String? = null // France
            )

            data class TeamColors(
                val primary: String? = null, // #ffffff
                val secondary: String? = null, // #ff0000
                val text: String? = null // #ff0000
            )
        }

        data class TransferFeeRaw(
            val value: Int? = null, // 0
            val currency: String? = null // EUR
        )
    }
}
