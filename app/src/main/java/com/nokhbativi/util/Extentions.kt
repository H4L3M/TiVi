package com.nokhbativi.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nokhbativi.model.database.DatabaseChannel
import com.nokhbativi.ui.player.PlayerViewModel
import com.nokhbativi.ui.main.MainViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun Query.snapshotFlow(): Flow<QuerySnapshot> = callbackFlow {
    val listenerRegistration = addSnapshotListener { value, error ->
        if (error != null) {
            close()
            return@addSnapshotListener
        }
        if (value != null)
            trySend(value)
    }
    awaitClose {
        listenerRegistration.remove()
    }
}

inline fun <reified T>get(name: String): Flow<List<T>> {
    val db = Firebase.firestore
    return db.collection(TIVI)
        .document(DATA)
        .collection(name)
        .snapshotFlow()
        .map { querySnapshot ->
            querySnapshot.toObjects(T::class.java)
        }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Context.setLandscape() {
    val activity = this.findActivity()
    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

fun ComponentActivity.hideSystemBars() {
    // Configure the behavior of the hidden system bars
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    // Hide both the status bar and the navigation bar
    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
}

fun ComponentActivity.showSystemBars() {
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
}

@SuppressLint("SourceLockedOrientationActivity")
fun Context.setPortrait() {
    val activity = this.findActivity()
    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}

fun Modifier.noRippleClickable(enabled: Boolean = true, onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick.invoke()
    }
}

fun Long.formatMinSec(): String {
    return if (this == 0L) {
        "..."
    } else {
        String.format(
            "%02d : %02d",
            TimeUnit.MILLISECONDS.toMinutes(this),
            TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(this)
            )
        )
    }
}

fun List<DatabaseChannel>.toMediaItems(): List<MediaItem> {
    return this.map {
        MediaItem.Builder().setUri(it.stream).setMediaId(it.id.toString()).setTag(it)
            .setMediaMetadata(MediaMetadata.Builder().setDisplayTitle(it.name.en).build())
            .build()
    }
}