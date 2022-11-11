package ma.nokhbativi.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ma.nokhbativi.ui.main.MainViewModel
import ma.nokhbativi.ui.single.SingleFeaturedEvent

@Composable
fun ListFeaturedEvents() {
    val mainViewModel: MainViewModel = hiltViewModel()
    val featuredEvents by mainViewModel.featuredEvents.collectAsState(initial = emptyList())
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = featuredEvents) { featuredEvent ->
//            SingleFeaturedEvent(featuredEvent = featuredEvent)
        }
    }
}