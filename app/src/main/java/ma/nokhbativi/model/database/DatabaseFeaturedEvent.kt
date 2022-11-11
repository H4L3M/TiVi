package ma.nokhbativi.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "FeaturedEvents")
data class DatabaseFeaturedEvent(
    @PrimaryKey
    val id: Int, // 10664824
    @Embedded
    val homeTeam: HomeTeam,
    @Embedded
    val awayTeam: AwayTeam,
    @Embedded
    val homeScore: HomeScore,
    @Embedded
    val awayScore: AwayScore,
) {
    data class HomeTeam(
        @ColumnInfo(name = "home_team_id")
        val id: Int, // 1968
        @ColumnInfo(name = "home_team_name")
        val name: String, // Santos
        @ColumnInfo(name = "home_team_short_name")
        val shortName: String, // Santos
        @ColumnInfo(name = "home_team_code_name")
        val nameCode: String, // SAN
    )

    data class AwayTeam(
        @ColumnInfo(name = "away_team_id")
        val id: Int, // 1980
        @ColumnInfo(name = "away_team_name")
        val name: String, // Juventude
        @ColumnInfo(name = "away_team_short_name")
        val shortName: String, // Juventude
        @ColumnInfo(name = "away_team_code_name")
        val nameCode: String, // JUV
    )

    data class HomeScore(
        @ColumnInfo(name = "home_display_score")
        val display: Int, // 1
    )

    data class AwayScore(
        @ColumnInfo(name = "away_display_score")
        val display: Int, // 0
    )
}
