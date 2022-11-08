package com.nokhbativi.ui.player

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.STATE_ENDED
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.nokhbativi.util.PLAYER_CONTROLS_VISIBILITY
import com.nokhbativi.util.noRippleClickable
import com.nokhbativi.util.setPortrait
import kotlinx.coroutines.delay

@Composable
fun PlayerView(
    modifier: Modifier = Modifier,
    exo: Exo,
    isFullScreen: Boolean,
    onTrailerChange: ((Int) -> Unit)? = null,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
    navigateBack: (() -> Unit)? = null
) {
    val context = LocalContext.current

    BackHandler {
        if (isFullScreen) {
            context.setPortrait()
            onFullScreenToggle.invoke(false)
        } else {
            navigateBack?.invoke()
        }
    }

    Box(modifier = modifier) {

        var shouldShowControls by remember { mutableStateOf(false) }

        var isPlaying by remember { mutableStateOf(exo.player.isPlaying) }

        var playbackState by remember { mutableStateOf(exo.player.playbackState) }

        var title by remember {
            mutableStateOf(exo.player.currentMediaItem?.mediaMetadata?.displayTitle.toString())
        }

        var videoTimer by remember { mutableStateOf(0L) }

        var totalDuration by remember { mutableStateOf(0L) }

        var bufferedPercentage by remember { mutableStateOf(0) }

        LaunchedEffect(key1 = shouldShowControls) {
            if (shouldShowControls) {
                delay(PLAYER_CONTROLS_VISIBILITY)
                shouldShowControls = false
            }
        }

        DisposableEffect(key1 = true) {
            val listener = object : Player.Listener {
                override fun onEvents(player: Player, events: Player.Events) {
                    super.onEvents(player, events)
                    isPlaying = player.isPlaying
                    totalDuration = player.duration
                    videoTimer = player.contentPosition
                    bufferedPercentage = player.bufferedPercentage
                    playbackState = player.playbackState
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)
                    onTrailerChange?.invoke(exo.player.currentPeriodIndex)
                    title = mediaItem?.mediaMetadata?.displayTitle.toString()
                }
            }

            exo.player.addListener(listener)

            onDispose {
                exo.player.removeListener(listener)
            }
        }

        VideoPlayer(
            modifier = Modifier.fillMaxSize(),
            exo = exo,
            onPlayerClick = {
                shouldShowControls = shouldShowControls.not()
            }
        )

        PlayerControls(
            modifier = Modifier.fillMaxSize(),
            isVisible = { shouldShowControls },
            isPlaying = { isPlaying },
            playbackState = { playbackState },
            totalDuration = { totalDuration },
            bufferedPercentage = { bufferedPercentage },
            getTitle = { title },
            isFullScreen = isFullScreen,
//            onPrevious = { playerWrapper.exoPlayer.seekToPrevious() },
//            onNext = { playerWrapper.exoPlayer.seekToNext() },
//            onReplay = { playerWrapper.exoPlayer.seekBack() },
//            onForward = { playerWrapper.exoPlayer.seekForward() },
            onPauseToggle = {
                when {
                    exo.player.isPlaying -> {
                        exo.player.pause()
                    }
                    exo.player.isPlaying.not() && playbackState == STATE_ENDED -> {
                        exo.player.seekTo(0, 0)
                        exo.player.playWhenReady = true
                    }
                    else -> {
                        exo.player.play()
                    }
                }
                isPlaying = isPlaying.not()
            },
            onSeekChanged = { position -> exo.player.seekTo(position.toLong()) },
            videoTimer = { videoTimer },
            onFullScreenToggle = onFullScreenToggle
        )
    }
}

@Composable
private fun VideoPlayer(
    modifier: Modifier = Modifier,
    exo: Exo,
    onPlayerClick: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .background(Color.Black)
            .testTag("VideoPlayerParent")
            .noRippleClickable {
                onPlayerClick.invoke()
            }
    ) {
        AndroidView(
            modifier = modifier
                .testTag("VideoPlayer"),
            factory = {
                StyledPlayerView(context).apply {
                    player = exo.player
                    useController = false
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlayerView() {
    val context = LocalContext.current
    PlayerView(
        modifier = Modifier.fillMaxSize(),
        exo = Exo(player = ExoPlayer.Builder(context).build()),
        isFullScreen = false,
        onFullScreenToggle = {}
    )
}