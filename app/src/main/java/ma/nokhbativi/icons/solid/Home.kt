package ma.nokhbativi.icons.solid

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path

val Icons.Rounded.SolidHome: ImageVector
    get() {
        if (_home != null) {
            return _home!!
        }
        _home = materialIcon(name = "Solid.Home") {
            materialPath {
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(256.0f, 319.84f)
                    curveToRelative(-35.35f, 0.0f, -64.0f, 28.65f, -64.0f, 64.0f)
                    verticalLineToRelative(128.0f)
                    horizontalLineToRelative(128.0f)
                    verticalLineToRelative(-128.0f)
                    curveTo(320.0f, 348.49f, 291.35f, 319.84f, 256.0f, 319.84f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(362.67f, 383.84f)
                    verticalLineToRelative(128.0f)
                    horizontalLineTo(448.0f)
                    curveToRelative(35.35f, 0.0f, 64.0f, -28.65f, 64.0f, -64.0f)
                    verticalLineTo(253.26f)
                    curveToRelative(0.0f, -11.08f, -4.3f, -21.73f, -12.01f, -29.7f)
                    lineToRelative(-181.29f, -195.99f)
                    curveToRelative(-31.99f, -34.61f, -85.98f, -36.74f, -120.59f, -4.75f)
                    curveToRelative(-1.64f, 1.52f, -3.23f, 3.1f, -4.75f, 4.75f)
                    lineTo(12.4f, 223.5f)
                    curveTo(4.45f, 231.5f, -0.0f, 242.31f, 0.0f, 253.58f)
                    verticalLineToRelative(194.26f)
                    curveToRelative(0.0f, 35.35f, 28.65f, 64.0f, 64.0f, 64.0f)
                    horizontalLineToRelative(85.33f)
                    verticalLineToRelative(-128.0f)
                    curveToRelative(0.4f, -58.17f, 47.37f, -105.68f, 104.07f, -107.04f)
                    curveTo(312.01f, 275.38f, 362.22f, 323.7f, 362.67f, 383.84f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(256.0f, 319.84f)
                    curveToRelative(-35.35f, 0.0f, -64.0f, 28.65f, -64.0f, 64.0f)
                    verticalLineToRelative(128.0f)
                    horizontalLineToRelative(128.0f)
                    verticalLineToRelative(-128.0f)
                    curveTo(320.0f, 348.49f, 291.35f, 319.84f, 256.0f, 319.84f)
                    close()
                }
            }
        }
        return _home!!
    }

private var _home: ImageVector? = null