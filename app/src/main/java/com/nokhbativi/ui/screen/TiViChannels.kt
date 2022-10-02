package com.nokhbativi.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nokhbativi.ui.main.MainViewModel
import com.nokhbativi.ui.single.SingleChannel

@Composable
fun ListChannels(code: String?, mainViewModel: MainViewModel) {

    val channels by mainViewModel.channels(code = code).collectAsState(initial = listOf())

    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        columns = GridCells.Fixed(1)
    ) {
        items(channels.size) { index ->
            val channel = channels[index]
            SingleChannel(
                channel = channel,
                onClick = { stream ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.parse(stream), "video/*")
                    context.startActivity(intent)
                },
            )
        }
    }
}