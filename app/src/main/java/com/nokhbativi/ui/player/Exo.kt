package com.nokhbativi.ui.player

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.google.android.exoplayer2.Player

@Immutable
@Stable
data class Exo(
    val player: Player
)