package com.nokhbativi.ui.single

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.coroutines.Dispatchers

@Composable
fun Image(data: String, modifier: Modifier = Modifier) {


    var visible by rememberSaveable { mutableStateOf(false) }
    SubcomposeAsyncImage(
        model = model(data = data),
        contentDescription = null,
        modifier = modifier.placeholder(
            visible = visible,
            highlight = PlaceholderHighlight.shimmer(),
        ),
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            visible = true
        } else {
            visible = false
            SubcomposeAsyncImageContent()
        }
    }
}

@Composable
fun model(data: String?) = ImageRequest.Builder(LocalContext.current)
    .data(data)
    .dispatcher(Dispatchers.IO)
    .diskCachePolicy(CachePolicy.ENABLED)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .networkCachePolicy(CachePolicy.ENABLED)
    .decoderFactory(SvgDecoder.Factory())
    .build()