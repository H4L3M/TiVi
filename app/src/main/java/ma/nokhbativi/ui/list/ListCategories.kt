package ma.nokhbativi.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ma.nokhbativi.ui.main.MainViewModel
import ma.nokhbativi.ui.single.SingleCountryHorizontal
import ma.nokhbativi.util.HOME_SPAN_COUNT

@Composable
fun ListCategories(onClick: (name: String, code: String) -> Unit) {

    val mainViewModel: MainViewModel = hiltViewModel()
    val categories by mainViewModel.categories.collectAsState(initial = emptyList())

//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        categories.forEach {category ->
//            SingleCountryHorizontal(
//                country = category
//            ) { name, code ->
//                onClick(name, code)
//            }
//        }
//    }
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        columns = GridCells.Fixed(3),
        userScrollEnabled = false
    ) {

        items(categories) { category ->
            SingleCountryHorizontal(
                country = category
            ) { name, code ->
                onClick(name, code)
            }
        }
    }
}