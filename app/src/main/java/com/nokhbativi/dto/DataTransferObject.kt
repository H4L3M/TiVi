package com.nokhbativi.dto

import com.nokhbativi.model.database.DatabaseCountry
import com.nokhbativi.network.NetworkCategoriesContainer

fun NetworkCategoriesContainer.asDatabaseModel(): Array<DatabaseCountry> {
    return countries.map { country ->
        DatabaseCountry(
            id = country.id,
            flag = country.flag,
            code = DatabaseCountry.Code(
                alpha2 = country.code.alpha2,
                alpha3 = country.code.alpha3,
                dial = country.code.dial,
            ),
            name = DatabaseCountry.Name(
                ar = country.name.ar,
                bg = country.name.bg,
                cs = country.name.cs,
                da = country.name.da,
                de = country.name.de,
                el = country.name.el,
                en = country.name.en,
                eo = country.name.eo,
                es = country.name.es,
                et = country.name.et,
                eu = country.name.eu,
                fi = country.name.fi,
                fr = country.name.fr,
                hu = country.name.hu,
                hy = country.name.hy,
                it = country.name.it,
                ja = country.name.ja,
                ko = country.name.ko,
                lt = country.name.lt,
                nl = country.name.nl,
                no = country.name.no,
                pl = country.name.pl,
                pt = country.name.pt,
                ro = country.name.ro,
                ru = country.name.ru,
                sk = country.name.sk,
                sv = country.name.sv,
                th = country.name.th,
                uk = country.name.uk,
                zh = country.name.zh,
            ),
            visible = country.visible
        )
    }.toTypedArray()
}