package ma.nokhbativi.ui.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ma.nokhbativi.ui.main.MainViewModel
import ma.nokhbativi.ui.single.SingleCountryHorizontal

@Composable
fun ListCountries(onClick: (name: String, code: String) -> Unit) {

    val mainViewModel: MainViewModel = hiltViewModel()

    val countries by mainViewModel.countries.collectAsState(initial = listOf())

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        columns = GridCells.Fixed(1),
        state = rememberLazyGridState()
    ) {
        items(countries) { country ->
            SingleCountryHorizontal(
                country = country
            ) { name, code ->
                onClick(name, code)
            }
        }
    }
}
