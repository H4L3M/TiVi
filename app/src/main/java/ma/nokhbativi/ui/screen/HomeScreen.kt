package ma.nokhbativi.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ma.nokhbativi.ui.list.ListPackages
import ma.nokhbativi.ui.main.MainViewModel
import ma.nokhbativi.ui.single.SingleCategory
import ma.nokhbativi.ui.single.SingleFeaturedEvent
import ma.nokhbativi.util.HOME_SPAN_COUNT

@Composable
fun HomeScreen(onClick: (name: String, code: String) -> Unit) {

    val mainViewModel: MainViewModel = hiltViewModel()
    val categories by mainViewModel.repository.categories.collectAsState(initial = emptyList())
    val featuredEvents by mainViewModel.repository.featuredEvents.collectAsState(initial = emptyList())

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(HOME_SPAN_COUNT),
    ) {
        item(
            span = { GridItemSpan(HOME_SPAN_COUNT) }
        ) {

            if (featuredEvents.isNotEmpty()) {
                featuredEvents.forEach { event ->
                    SingleFeaturedEvent(featuredEvent = event)
                }
            }
        }
        item(
            span = { GridItemSpan(HOME_SPAN_COUNT) }
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .wrapContentHeight()
                    .padding(start = 16.dp),
                text = "Packages",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
            )
        }
        item(
            span = { GridItemSpan(HOME_SPAN_COUNT) }
        ) {
            ListPackages(onClick = onClick)
        }
        item(
            span = { GridItemSpan(HOME_SPAN_COUNT) }
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .wrapContentHeight()
                    .padding(start = 16.dp),
                text = "Categories",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
            )
        }
        items(items = categories) { category ->
            SingleCategory(category = category, onClick = onClick)
//            SingleCountryHorizontal(country = category, onClick = onClick)
        }
    }
}