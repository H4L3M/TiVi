package com.nokhbativi.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Channels")
data class DatabaseChannel(
    @PrimaryKey
    val id: Long,
    @Embedded
    val name: Name,
    val logo: String,
    @Embedded
    val code: Code,
    val stream: String,
    val priority: Int,
    @ColumnInfo(name = "user_agent")
    val userAgent: String,
    val visible: Boolean,
) {
    data class Name(
        @ColumnInfo(name = "name_ar")
        val ar: String,
        @ColumnInfo(name = "name_en")
        val en: String,
    )

    data class Code(
        val country: String,
        @ColumnInfo(name = "package")
        val pack: String,
        val category: String,
    )
}
