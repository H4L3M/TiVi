package com.nokhbativi.ui.single

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers

@Composable
fun CoilImage(data: String?, modifier: Modifier = Modifier) {
    if (data != null) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data)
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED).dispatcher(Dispatchers.IO)
                .decoderFactory(SvgDecoder.Factory()).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier,
        )
    }
}