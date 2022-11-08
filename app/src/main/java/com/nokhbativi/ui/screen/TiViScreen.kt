package com.nokhbativi.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.nokhbativi.ui.list.ListCategories
import com.nokhbativi.ui.list.ListCountries
import com.nokhbativi.ui.list.ListPackages


@Composable
fun TiViScreen(onClick: (name: String, code: String) -> Unit) {
    Column {
        Text(text = "Packages", fontSize = 24.sp)
        ListPackages(onClick = onClick)
        Text(text = "Categories", fontSize = 24.sp)
        ListCategories(onClick = onClick)
        Text(text = "Countries", fontSize = 24.sp)
        ListCountries(onClick = onClick)
    }
}