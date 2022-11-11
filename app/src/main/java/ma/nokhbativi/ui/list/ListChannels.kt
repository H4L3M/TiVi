package ma.nokhbativi.ui.list

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ma.nokhbativi.ui.player.PlayerActivity
import ma.nokhbativi.ui.main.MainViewModel
import ma.nokhbativi.ui.single.SingleChannelHorizontal

@Composable
fun ListChannels(code: String) {

    val mainViewModel: MainViewModel = hiltViewModel()

    val channels by mainViewModel.channels(code = code).collectAsState(initial = emptyList())

    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(1),
        state = rememberLazyGridState()
    ) {
        items(channels.size) { index ->
            val channel = channels[index]
            SingleChannelHorizontal(
                channel = channel,
                onClick = {
//                    val intent = Intent(Intent.ACTION_VIEW)
                    val intent = Intent(context, PlayerActivity::class.java)
//                    intent.setDataAndType(Uri.parse(stream), "video/*")
                    intent.putExtra("url",it.stream)
                    intent.putExtra("user_agent", it.userAgent)
                    context.startActivity(intent)

//                    Toast.makeText(context, channel.stream, Toast.LENGTH_LONG).show()
                },
            )
        }
    }
}