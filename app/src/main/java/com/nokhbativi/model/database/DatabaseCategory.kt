package com.nokhbativi.model.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class DatabaseCategory constructor(
    @PrimaryKey
    val id: Int,
    val logo: String,
    @Embedded
    val code: Code,
    @Embedded
    val name: Name,
    val priority: Int,
    val visible: Boolean,
) {
    data class Name(
        val ar: String,
        val en: String,
    )

    data class Code(
        val alpha: String,
        val type: String,
    )
}