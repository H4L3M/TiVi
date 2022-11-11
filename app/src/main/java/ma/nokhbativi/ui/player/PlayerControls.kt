package ma.nokhbativi.ui.player

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ma.nokhbativi.R
import ma.nokhbativi.ui.theme.Mine
import ma.nokhbativi.util.setLandscape
import ma.nokhbativi.util.setPortrait

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PlayerControls(
    modifier: Modifier = Modifier,
    isVisible: () -> Boolean,
    isPlaying: () -> Boolean,
    videoTimer: () -> Long,
    bufferedPercentage: () -> Int,
    playbackState: () -> Int,
    getTitle: () -> String,
    totalDuration: () -> Long,
    isFullScreen: Boolean,
    onPauseToggle: () -> Unit,
    onSeekChanged: (newValue: Float) -> Unit,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
) {

    val visible = remember(isVisible()) { isVisible() }

    val playing = remember(isPlaying()) { isPlaying() }

    val duration = remember(totalDuration()) { totalDuration().coerceAtLeast(0) }

    val timer = remember(videoTimer()) { videoTimer() }

    val title = remember(getTitle()) { getTitle() }

    val buffer = remember(bufferedPercentage()) { bufferedPercentage() }

    val playerState = remember(playbackState()) {
        playbackState()
    }

    val context = LocalContext.current

    val controlButtonModifier: Modifier = remember(isFullScreen) {
        if (isFullScreen) {
            Modifier
                .padding(horizontal = 8.dp)
                .size(56.dp)
        } else {
            Modifier.size(48.dp)
        }
    }
    if (buffer <= 0 && isPlaying.invoke().not()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.6f))
        ) {

            val indicatorRef = createRef()

            CircularProgressIndicator(
                modifier = controlButtonModifier.constrainAs(indicatorRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                color = Color.White,
                strokeWidth = 1.dp
            )
        }
    } else {

        AnimatedVisibility(
            modifier = modifier,
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.6f))
            ) {

                val (playPauseRef, fullscreenRef, seekBarRef) = createRefs()
                IconButton(
                    modifier = controlButtonModifier.constrainAs(playPauseRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                    onClick = onPauseToggle
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(
                            id =
                            when {
                                playing -> {
                                    com.google.android.exoplayer2.ui.R.drawable.exo_ic_pause_circle_filled
                                }
                                else -> {
                                    com.google.android.exoplayer2.ui.R.drawable.exo_ic_play_circle_filled
                                }
                            }
                        ),
                        contentDescription = stringResource(id = R.string.toggle_play)
                    )
                }

                IconButton(
                    modifier = Modifier
                        .testTag("FullScreenToggleButton")
                        .padding(end = 16.dp, bottom = 16.dp)
                        .size(24.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight: Int -> fullHeight }
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { fullHeight: Int -> fullHeight }
                            )
                        )
                        .constrainAs(fullscreenRef) {
                            end.linkTo(parent.end)
                            bottom.linkTo(seekBarRef.top)
                        },
                    onClick = {
                        if (isFullScreen.not()) {
                            context.setLandscape()
                        } else {
                            context.setPortrait()
                        }.also {
                            onFullScreenToggle.invoke(isFullScreen.not())
                        }
                    }
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(
                            id = if (isFullScreen) {
                                com.google.android.exoplayer2.ui.R.drawable.exo_controls_fullscreen_exit
                            } else {
                                com.google.android.exoplayer2.ui.R.drawable.exo_controls_fullscreen_enter
                            }
                        ),
                        contentDescription = stringResource(id = R.string.toggle_full_screen)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("VideoSeek")
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = { fullHeight: Int -> fullHeight }
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { fullHeight: Int -> fullHeight }
                            )
                        )
                        .constrainAs(seekBarRef) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    ) {
                        Slider(
                            value = buffer.toFloat(),
                            enabled = false,
                            onValueChange = { /*do nothing*/ },
                            valueRange = 0f..100f,
                            colors =
                            SliderDefaults.colors(
                                activeTrackColor = Mine,
                                activeTickColor = Mine,
                                disabledThumbColor = Color.Transparent,
                                disabledActiveTrackColor = MaterialTheme.colorScheme.onError
                            )
                        )
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlayerControls() {
    PlayerControls(
        modifier = Modifier.fillMaxSize(),
        isVisible = { true },
        isPlaying = { true },
        videoTimer = { 0L },
        bufferedPercentage = { 50 },
        playbackState = { 1 },
        getTitle = { "" },
        totalDuration = { 0 },
        isFullScreen = false,
        onPauseToggle = {},
        onSeekChanged = {},
        onFullScreenToggle = {},
    )
}