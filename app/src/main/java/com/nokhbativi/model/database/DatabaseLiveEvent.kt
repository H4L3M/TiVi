package com.nokhbativi.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "LiveEvents")
data class DatabaseLiveEvent(
    @PrimaryKey
    val id: Int, // 10433883
    @ColumnInfo(name = "event_overage")
    val coverage: Int?, // -1
    @ColumnInfo(name = "winner_code")
    val winnerCode: Int?, // 0
    @ColumnInfo(name = "away_red_cards")
    val awayRedCards: Int?, // 2
    @ColumnInfo(name = "home_red_cards")
    val homeRedCards: Int?, // 2
    @ColumnInfo(name = "event_start_timestamp")
    val startTimestamp: Int?, // 1664816400
    @ColumnInfo(name = "event_last_period")
    val lastPeriod: String?, // period2
    @Embedded
    val time: Time?,
    @Embedded
    val status: Status?,
    @Embedded
    val homeTeam: HomeTeam,
    @Embedded
    val awayTeam: AwayTeam,
    @Embedded
    val homeScore: HomeScore,
    @Embedded
    val awayScore: AwayScore,
    @Embedded
    val roundInfo: RoundInfo?,
    @Embedded
    val tournament: Tournament,
    @Embedded
    val statusTime: StatusTime?,
    @ColumnInfo(name = "event_final_result_only")
    val finalResultOnly: Boolean?, // false
    @ColumnInfo(name = "event_has_goal_highlights")
    val hasGlobalHighlights: Boolean?, // false
    @ColumnInfo(name = "event_has_player_heat_map")
    val hasEventPlayerHeatMap: Boolean?, // true
    @ColumnInfo(name = "event_has_player_statistics")
    val hasEventPlayerStatistics: Boolean?, // true
) {
    data class Tournament(
        @ColumnInfo(name = "tournament_id")
        val id: Int, // 62
        @ColumnInfo(name = "tournament_name")
        val name: String, // Super Lig
        @ColumnInfo(name = "tournament_priority")
        val priority: Int, // 310
//        @Embedded
//        val category: Category?,
        @Embedded
        val uniqueTournament: UniqueTournament?,
    ) {
//        data class Category(
//            @ColumnInfo(name = "tournament_category_id")
//            val id: Int?, // 46
//            @ColumnInfo(name = "tournament_category_name")
//            val name: String?, // Turkey
//            @ColumnInfo(name = "tournament_category_flag")
//            val flag: String?, // turkey
//            @ColumnInfo(name = "tournament_category_alpha2")
//            val alpha2: String?, // TR
//        )

        data class UniqueTournament(
            @ColumnInfo(name = "unique_tournament_id")
            val id: Int?, // 52
            @ColumnInfo(name = "unique_tournament_name")
            val name: String?, // Süper Lig
            @ColumnInfo(name = "unique_tournament_user_count")
            val userCount: Int?, // 86955
//            @Embedded
//            val category: UniqueCategory,
            @ColumnInfo(name = "unique_tournament_has_position_graph")
            val hasPositionGraph: Boolean?, // true
            @ColumnInfo(name = "unique_tournament_has_player_statistics")
            val hasEventPlayerStatistics: Boolean?, // true
            @ColumnInfo(name = "unique_tournament_display_inverse_home_away_teams")
            val displayInverseHomeAwayTeams: Boolean?, // false
        )
//        {
//            data class UniqueCategory(
//                @ColumnInfo(name = "unique_tournament_category_id")
//                val id: Int, // 46
//                @ColumnInfo(name = "unique_tournament_category_name")
//                val name: String, // Turkey
//                @ColumnInfo(name = "unique_tournament_category_flag")
//                val flag: String, // turkey
//                @ColumnInfo(name = "unique_tournament_category_alpha2")
//                val alpha2: String // TR
//            )
//        }
    }

    data class RoundInfo(
        @ColumnInfo(name = "event_round")
        val round: Int?, // 8
    )

    data class Status(
        @ColumnInfo(name = "event_status_code")
        val code: Int?, // 31
        @ColumnInfo(name = "event_status_type")
        val type: String?, // inprogress
        @ColumnInfo(name = "event_status_description")
        val description: String?, // Halftime
    )

    data class HomeTeam(
        @ColumnInfo(name = "home_team_id")
        val id: Int, // 4954
        @ColumnInfo(name = "home_team_name")
        val name: String, // Fatih Karagümrük
        @ColumnInfo(name = "home_team_name_code")
        val nameCode: String?, // KAR
        @ColumnInfo(name = "home_team_short_name")
        val shortName: String, // Karagümrük
        @ColumnInfo(name = "home_team_type")
        val type: Int?, // 0
        @ColumnInfo(name = "home_team_user_count")
        val userCount: Int?, // 7764
        @ColumnInfo(name = "home_team_disabled")
        val disabled: Boolean?, // false
        @ColumnInfo(name = "home_team_national")
        val national: Boolean?, // false
    )

    data class AwayTeam(
        @ColumnInfo(name = "away_team_id")
        val id: Int, // 4954
        @ColumnInfo(name = "away_team_name")
        val name: String, // Fatih Karagümrük
        @ColumnInfo(name = "away_team_code")
        val nameCode: String?, // KAR
        @ColumnInfo(name = "away_team_short_name")
        val shortName: String, // Karagümrük
        @ColumnInfo(name = "away_team_type")
        val type: Int?, // 0
        @ColumnInfo(name = "away_team_user_count")
        val userCount: Int?, // 7764
        @ColumnInfo(name = "away_team_disabled")
        val disabled: Boolean?, // false
        @ColumnInfo(name = "away_team_national")
        val national: Boolean?, // false
    )

    data class HomeScore(
        @ColumnInfo(name = "home_team_current_score")
        val current: Int?, // 1
        @ColumnInfo(name = "home_team_display_score")
        val display: Int?, // 1
        @ColumnInfo(name = "home_team_period1_score")
        val period1: Int?, // 1
        @ColumnInfo(name = "home_team_normal_score")
        val normaltime: Int? // 0
    )

    data class AwayScore(
        @ColumnInfo(name = "away_team_current_score")
        val current: Int?, // 0
        @ColumnInfo(name = "away_team_display_score")
        val display: Int?, // 0
        @ColumnInfo(name = "away_team_period1_score")
        val period1: Int?, // 0
        @ColumnInfo(name = "away_team_normal_score")
        val normaltime: Int? // 1
    )

    data class Time(
        @ColumnInfo(name = "event_max_time")
        val max: Int?, // 5400
        @ColumnInfo(name = "event_extra_time")
        val extra: Int?, // 540
        @ColumnInfo(name = "event_initial_time")
        val initial: Int?, // 2700
        @ColumnInfo(name = "event_first_injury_time")
        val injuryTime1: Int?, // 3
        @ColumnInfo(name = "event_current_period_start_timestamp")
        val currentPeriodStartTimestamp: Int?, // 1664819602
    )

    data class StatusTime(
        @ColumnInfo(name = "event_prefix_status_time")
        val prefix: String?,
        @ColumnInfo(name = "event_max_status_time")
        val max: Int?, // 5400
        @ColumnInfo(name = "event_extra_status_time")
        val extra: Int?, // 540
        @ColumnInfo(name = "event_initial_status_time")
        val initial: Int?, // 2700
        @ColumnInfo(name = "event_status_time_timestamp")
        val timestamp: Int?, // 1664820210
    )
}