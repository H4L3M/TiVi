package com.nokhbativi.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channels")
data class DatabaseChannel(
    @PrimaryKey
    var id: Int,
    @Embedded
    var name: Name,
    var logo: String,
    @Embedded
    var code: Code,
    @ColumnInfo(name = "user_agent")
    var userAgent: String?,
    var stream: String?,
    var status: String?,
    @ColumnInfo(name = "is_new")
    var isNew: Boolean,
    var visible: Boolean,
) {

    data class Name(
        var ar: String?,
        var en: String?,
    )

    data class Code(
        var country: String?,
        var category: String?,
        @ColumnInfo(name = "package")
        var pack: String?,
    )
}
