package com.nokhbativi.ui.player

import androidx.lifecycle.SavedStateHandle
import com.nokhbativi.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(handle: SavedStateHandle) :
    BaseViewModel<PlayerState, Nothing>(handle) {

    override fun createInitialState() = PlayerState()

    fun toggleFullScreen(isFullScreen: Boolean) = intent {
        reduce {
            if (isFullScreen) {
                state.copy(isFullScreen = true)
            } else {
                state.copy(isFullScreen = false)
            }
        }
    }
}