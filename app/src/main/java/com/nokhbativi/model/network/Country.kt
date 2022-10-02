package com.nokhbativi.model.network

data class Country(
    val id: Int,
    val flag: String,
    val code: Code,
    val name: Name,
    val visible: Boolean,
) {
    data class Code(
        val alpha2: String,
        val alpha3: String,
        val dial: String,
    )

    data class Name(
        val ar: String,
        val bg: String,
        val cs: String,
        val da: String,
        val de: String,
        val el: String,
        val en: String,
        val eo: String,
        val es: String,
        val et: String,
        val eu: String,
        val fi: String,
        val fr: String,
        val hu: String,
        val hy: String,
        val it: String,
        val ja: String,
        val ko: String,
        val lt: String,
        val nl: String,
        val no: String,
        val pl: String,
        val pt: String,
        val ro: String,
        val ru: String,
        val sk: String,
        val sv: String,
        val th: String,
        val uk: String,
        val zh: String,
    )
}