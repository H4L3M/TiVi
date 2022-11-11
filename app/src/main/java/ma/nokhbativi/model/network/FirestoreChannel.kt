package ma.nokhbativi.model.network


data class FirestoreChannel(
    val id: Long? = null,
    val name: Name? = null,
    val logo: String? = null,
    val code: Code? = null,
    val stream: String? = null,
    val priority: Int? = null,
    val userAgent: String? = null,
    val visible: Boolean? = null,
) {

    constructor(): this(
        id = null,
        name = Name(ar = null, en = null),
        logo = null,
        code = null,
        stream = null,
        priority = null,
        userAgent = null,
        visible = null
    )

    data class Name(
        val ar: String? = null,
        val en: String? = null,
    )

    data class Code(
        val country: String? = null,
        val pack: String? = null,
        val category: String? = null
    )
}