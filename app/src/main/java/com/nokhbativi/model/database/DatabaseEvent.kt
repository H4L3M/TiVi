package com.nokhbativi.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class DatabaseEvent(
    @PrimaryKey
    @ColumnInfo(name = "event_id") val id: Int?,
    @Embedded val tournament: Tournament,
    @Embedded val awayTeam: AwayTeam,
    @Embedded val homeTeam: HomeTeam,
    @Embedded val status: Status?,
    @Embedded val time: Time?,
    @ColumnInfo(name = "custom_id") val customId: String?,
    @ColumnInfo(name = "fina_result_only") val finalResultOnly: Boolean?,
    @ColumnInfo(name = "has_event_player_heat_map") val hasEventPlayerHeatMap: Boolean?,
    @ColumnInfo(name = "has_event_player_statistics") val hasEventPlayerStatistics: Boolean?,
    @ColumnInfo(name = "has_global_highlights") val hasGlobalHighlights: Boolean?,
    @ColumnInfo(name = "start_timestamp") val startTimestamp: Long?,
    @ColumnInfo(name = "winner_code") val winnerCode: Int?,
    @Embedded
    val roundInfo: RoundInfo?,
)

data class AwayScore(
    @ColumnInfo(name = "away_team_score_current") val current: Int? = 0,
    @ColumnInfo(name = "away_team_score_display") val display: Int? = 0,
    @ColumnInfo(name = "away_team_score_normal_time") val normaltime: Int? = 0,
    @ColumnInfo(name = "away_team_score_first_period") val period1: Int? = 0,
    @ColumnInfo(name = "away_team_score_second_period") val period2: Int? = 0
)

data class AwayTeam(
    @ColumnInfo(name = "away_team_id") val id: Int,
    @ColumnInfo(name = "away_team_name") val name: String,
    @ColumnInfo(name = "away_team_short_name") val shortName: String,
    @ColumnInfo(name = "away_team_slug") val slug: String,
    @ColumnInfo(name = "away_team_type") val type: Int,
    @ColumnInfo(name = "away_team_user_count") val userCount: Int?,
    @ColumnInfo(name = "disabled") val disabled: Boolean?,
    @Embedded val awayScore: AwayScore?,
)

data class HomeScore(
    @ColumnInfo(name = "home_team_score_current") val current: Int? = 0,
    @ColumnInfo(name = "home_team_score_display") val display: Int? = 0,
    @ColumnInfo(name = "home_team_score_normal_time") val normaltime: Int? = 0,
    @ColumnInfo(name = "home_team_score_first_period") val period1: Int? = 0,
    @ColumnInfo(name = "home_team_score_second_period") val period2: Int? = 0
)

data class HomeTeam(
    @ColumnInfo(name = "home_team_id") val id: Int,
    @ColumnInfo(name = "home_team_name") val name: String,
    @ColumnInfo(name = "home_short_name") val shortName: String,
    @ColumnInfo(name = "home_team_slug") val slug: String,
    @ColumnInfo(name = "home_team_type") val type: Int,
    @ColumnInfo(name = "home_team_user_count") val userCount: Int?,
    @Embedded val homeScore: HomeScore?,
)

data class RoundInfo(
    @ColumnInfo(name = "round") val round: Int?,
)

data class Status(
    @ColumnInfo(name = "status_code") val code: Int?,
    @ColumnInfo(name = "status_type") val type: String?,
    @ColumnInfo(name = "status_description") val description: String?,
)

data class Time(
    @ColumnInfo(name = "injury_time_first") val injuryTime1: Int?,
    @ColumnInfo(name = "injury_time_second") val injuryTime2: Int?,
    @ColumnInfo(name = "current_period_start_timestamp") val currentPeriodStartTimestamp: Long?,
)

data class Tournament(
    @ColumnInfo(name = "tournament_id") val id: Int?,
    @ColumnInfo(name = "tournament_name") val name: String?,
    @ColumnInfo(name = "tournament_slug") val slug: String?,
    @ColumnInfo(name = "tournament_priority") val priority: Int?,
    @Embedded val category: Category?,
    @Embedded val uniqueTournament: UniqueTournament?,
)

data class UniqueTournament(
    @Embedded val category: UniqueCategory?,
    @ColumnInfo(name = "unique_tournament_id") val id: Int?,
    @ColumnInfo(name = "unique_tournament_name") val name: String?,
    @ColumnInfo(name = "unique_tournament_slug") val slug: String?,
    @ColumnInfo(name = "unique_tournament_user_count") val userCount: Int?,
    @ColumnInfo(name = "has_position_graph") val hasPositionGraph: Boolean?,
    @ColumnInfo(name = "has_event_players_statistics") val hasEventPlayerStatistics: Boolean?,
    @ColumnInfo(name = "display_inverse_home_away_team") val displayInverseHomeAwayTeams: Boolean?,
)

data class Category(
    @ColumnInfo(name = "category_id") val id: Int?,
    @ColumnInfo(name = "category_name") val name: String?,
    @ColumnInfo(name = "category_slug") val slug: String?,
    @ColumnInfo(name = "category_flag") val flag: String?,
    @ColumnInfo(name = "category_alpha2") val alpha2: String?,
)

data class UniqueCategory(
    @ColumnInfo(name = "unique_category_id") val id: Int?,
    @ColumnInfo(name = "unique_category_name") val name: String?,
    @ColumnInfo(name = "unique_category_slug") val slug: String?,
    @ColumnInfo(name = "unique_category_flag") val flag: String?,
    @ColumnInfo(name = "unique_category_alpha2") val alpha2: String?,
)