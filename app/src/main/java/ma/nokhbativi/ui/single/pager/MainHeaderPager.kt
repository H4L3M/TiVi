package ma.nokhbativi.ui.single.pager

import android.util.Log
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ma.nokhbativi.ui.main.MainViewModel
import ma.nokhbativi.ui.single.SingleEvent
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainHeaderPager() {

    val viewModel: MainViewModel = hiltViewModel()
    val events by viewModel.liveEvents.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
    ) {
        // Display 10 items
        val pageCount = 10

        // We start the pager in the middle of the raw number of pages
        val loopingCount = Int.MAX_VALUE
        val startIndex = loopingCount / 2
        val pagerState = rememberPagerState(initialPage = startIndex)

        fun pageMapper(index: Int): Int {
            return (index - startIndex).floorMod(pageCount)
        }

        HorizontalPager(
            // Set the raw page count to a really large number
            count = loopingCount,
            state = pagerState,
            // Add 32.dp horizontal padding to 'center' the pages
            contentPadding = PaddingValues(horizontal = 16.dp),
            // Add some horizontal spacing between items
            itemSpacing = 4.dp,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { _ ->
            // We calculate the page from the given index
//                val page = pageMapper(index)
//            PagerItem(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(1f)
//            )

        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            pageCount = pageCount,
            pageIndexMapping = ::pageMapper
        )

        val loopState = remember {
            mutableStateOf(true)
        }

//        LoopControl(loopState, Modifier.align(Alignment.CenterHorizontally))

        var underDragging by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = Unit) {
            pagerState.interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> underDragging = true
                    is PressInteraction.Release -> underDragging = false
                    is PressInteraction.Cancel -> underDragging = false
                    is DragInteraction.Start -> underDragging = true
                    is DragInteraction.Stop -> underDragging = false
                    is DragInteraction.Cancel -> underDragging = false
                }
            }
        }

        val looping = loopState.value
        if (underDragging.not() && looping) {
            LaunchedEffect(key1 = underDragging) {
                try {
                    while (true) {
                        delay(10000L)
                        val current = pagerState.currentPage
                        val currentPos = pageMapper(current)
                        val nextPage = current + 1
                        if (underDragging.not()) {
                            val toPage = nextPage.takeIf { nextPage < pagerState.pageCount }
                                ?: (currentPos + startIndex + 1)
                            if (toPage > current) {
                                pagerState.animateScrollToPage(toPage)
                            } else {
                                pagerState.scrollToPage(toPage)
                            }
                        }
                    }
                } catch (e: CancellationException) {
                    Log.i("page", "Launched paging cancelled")
                }
            }
        }
    }
}

@Composable
fun LoopControl(
    loopState: MutableState<Boolean>,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = { loopState.value = loopState.value.not() },
        modifier = modifier
    ) {
        val icon = if (loopState.value) {
            Icons.Default.Close
        } else {
            Icons.Default.PlayArrow
        }
        Icon(imageVector = icon, contentDescription = null)
    }
}

private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}