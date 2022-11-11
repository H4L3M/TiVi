package ma.nokhbativi.model.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class DatabasePost(
    @PrimaryKey
    val id: Int,
    val title: String,
    val excerpt: String,
    @ColumnInfo(name = "seo_content")
    val sEOContent: String,
//    val gallery: List<String>,
    val image: String,
    @ColumnInfo(name = "img_caption")
    val imgCaption: String,
    val created: String,
    val updated: String,
    val hideUpdDate: String?,
    @ColumnInfo(name = "img_source")
    val imgSource: String?
)