package ma.nokhbativi.ui.player

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.view.WindowCompat
import androidx.lifecycle.*
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import ma.nokhbativi.ads.UnityAdManager
import ma.nokhbativi.ui.theme.TiViTheme
import ma.nokhbativi.util.*


class PlayerActivity : ComponentActivity() {

    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var dataSourceFactory: DataSource.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        lifecycle.addObserver(UnityAdManager(this))

        val url = intent.getStringExtra("url")
        val userAgent = intent.getStringExtra("user_agent")
        dataSourceFactory = DefaultDataSourceFactory(this, userAgent)

        setContent {
            hideSystemBars()
            KeepScreenOn()
            TiViTheme {
                PlayStream(
                    url = url.toString(),
                    onFullScreenToggle = playerViewModel::toggleFullScreen,
                    navigateBack = this::finish
                )
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        hideSystemBars()
    }

    @Composable
    private fun PlayStream(
        url: String,
        onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
        navigateBack: () -> Unit
    ) {
        val context = LocalContext.current

        val player = remember {
            ExoPlayer.Builder(context)
                .setSeekBackIncrementMs(PLAYER_SEEK_BACK_INCREMENT)
                .setSeekForwardIncrementMs(PLAYER_SEEK_FORWARD_INCREMENT)
                .setMediaSourceFactory(
                    DefaultMediaSourceFactory(this)
                    .setDataSourceFactory(dataSourceFactory))
                .build()
                .apply {
                    setMediaItem(MediaItem.fromUri(url))
                    prepare()
                    playWhenReady = true
                }
        }

        val lifecycle = LocalLifecycleOwner.current.lifecycle

        DisposableEffect(key1 = Unit) {
            val observer = object : DefaultLifecycleObserver {
                override fun onStart(owner: LifecycleOwner) {
                    super.onStart(owner)
                    if (player.isPlaying.not()) {
                        player.play()
                    }
                }

                override fun onStop(owner: LifecycleOwner) {
                    player.pause()
                    super.onStop(owner)
                }
            }

            lifecycle.addObserver(observer)

            onDispose {
                lifecycle.removeObserver(observer)
                player.release()
            }
        }

        PlayerConfig(
            exo = Exo(player),
            onFullScreenToggle = onFullScreenToggle,
            navigateBack = navigateBack
        )
    }

    @Composable
    private fun PlayerConfig(
        exo: Exo,
        onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
        navigateBack: () -> Unit
    ) {

        val configuration = LocalConfiguration.current

        var playingIndex by remember { mutableStateOf(0) }

        fun onTrailerChange(index: Int) {
            playingIndex = index
            exo.player.seekTo(playingIndex, C.TIME_UNSET)
            exo.player.playWhenReady = true
        }

        when (configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                PortraitView(
                    exo = exo,
                    onTrailerChange = { index -> onTrailerChange(index) },
                    onFullScreenToggle = onFullScreenToggle,
                    navigateBack = navigateBack
                )
            }
            else -> {
                LandscapeView(
                    exo = exo, onFullScreenToggle = onFullScreenToggle
                )
            }
        }
    }

    @Composable
    private fun PortraitView(
        exo: Exo,
        onTrailerChange: (Int) -> Unit,
        onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
        navigateBack: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .background(color = Color.Black)
        ) {
            PlayerView(
                modifier = Modifier
                    .weight(weight = 1f, fill = true)
                    .background(color = Color.Black),
                exo = exo,
                isFullScreen = false,
                onTrailerChange = onTrailerChange,
                onFullScreenToggle = onFullScreenToggle,
                navigateBack = navigateBack
            )
        }
    }

    @Composable
    private fun LandscapeView(
        exo: Exo,
        onFullScreenToggle: (isFullScreen: Boolean) -> Unit
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            PlayerView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
                exo = exo,
                isFullScreen = true,
                onFullScreenToggle = onFullScreenToggle
            )
        }
    }
}