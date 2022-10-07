package com.nokhbativi.model.network

data class FirestoreCategory constructor(
    val id: Int? = null,
    val name: Name? = null,
    val logo: String? = null,
    val code: Code? = null,
    val priority: Int? = null,
    val visible: Boolean? = null,
) {
    constructor() : this(
        id = 0,
        name = Name(ar = null, en = null),
        logo = null,
        code = Code(alpha = null, type = null),
        visible = false
    )

    data class Name(
        val ar: String? = null,
        val en: String? = null,
    )

    data class Code(
        val alpha: String? = null,
        val type: String? = null,
    )
}