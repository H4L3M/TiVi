package com.nokhbativi.icons.rounded


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val Icons.Rounded.Whistle: ImageVector
    get() {
        if (_whistle != null) {
            return _whistle!!
        }
        _whistle = materialIcon(name = "Rounded.Football") {
            materialPath {
                moveTo(8.0f, 12.0f)
                arcToRelative(4.0f, 4.0f, 0.0f, true, false, 4.0f, 4.0f)
                arcTo(4.0f, 4.0f, 0.0f, false, false, 8.0f, 12.0f)
                close()
                moveTo(8.0f, 18.0f)
                arcToRelative(2.0f, 2.0f, 0.0f, true, true, 2.0f, -2.0f)
                arcTo(2.0f, 2.0f, 0.0f, false, true, 8.0f, 18.0f)
                close()
                moveTo(21.5f, 8.0f)
                lineTo(8.229f, 8.0f)
                arcTo(8.152f, 8.152f, 0.0f, false, false, 0.022f, 15.394f)
                arcTo(8.0f, 8.0f, 0.0f, false, false, 8.006f, 24.0f)
                arcToRelative(8.264f, 8.264f, 0.0f, false, false, 1.279f, -0.1f)
                arcToRelative(7.956f, 7.956f, 0.0f, false, false, 5.908f, -4.46f)
                arcTo(7.9f, 7.9f, 0.0f, false, false, 16.0f, 16.152f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 0.786f, -1.006f)
                lineToRelative(5.161f, -0.938f)
                arcTo(2.5f, 2.5f, 0.0f, false, false, 24.0f, 11.748f)
                lineTo(24.0f, 10.5f)
                arcTo(2.5f, 2.5f, 0.0f, false, false, 21.5f, 8.0f)
                close()
                moveTo(22.0f, 11.748f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, -0.411f, 0.492f)
                lineToRelative(-5.162f, 0.939f)
                arcTo(2.981f, 2.981f, 0.0f, false, false, 14.0f, 16.112f)
                arcToRelative(5.938f, 5.938f, 0.0f, false, true, -0.607f, 2.46f)
                arcToRelative(5.986f, 5.986f, 0.0f, false, true, -11.375f, -3.03f)
                arcTo(6.144f, 6.144f, 0.0f, false, true, 8.229f, 10.0f)
                lineTo(21.5f, 10.0f)
                arcToRelative(0.5f, 0.5f, 0.0f, false, true, 0.5f, 0.5f)
                close()
                moveTo(9.0f, 4.0f)
                lineTo(9.0f, 1.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 2.0f, 0.0f)
                lineTo(11.0f, 4.0f)
                arcTo(1.0f, 1.0f, 0.0f, false, true, 9.0f, 4.0f)
                close()
                moveTo(12.607f, 4.553f)
                lineTo(14.107f, 1.553f)
                arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.79f, 0.894f)
                lineToRelative(-1.5f, 3.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, true, true, -1.789f, -0.894f)
                close()
                moveTo(4.107f, 2.453f)
                arcToRelative(1.0f, 1.0f, 0.0f, true, true, 1.786f, -0.9f)
                lineToRelative(1.516f, 3.0f)
                arcToRelative(1.0f, 1.0f, 0.0f, true, true, -1.785f, 0.9f)
                close()
            }
        }
        return _whistle!!
    }

private var _whistle: ImageVector? = null