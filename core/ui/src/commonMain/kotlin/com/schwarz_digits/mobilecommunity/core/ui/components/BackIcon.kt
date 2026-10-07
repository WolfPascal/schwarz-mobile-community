package com.schwarz_digits.mobilecommunity.core.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Standard Material design back arrow navigation icon vector.
 * Built with standard Compose graphics tokens without requiring the heavy material-icons-core dependency.
 */
val ArrowBackIcon: ImageVector by lazy {
    ImageVector
        .Builder(
            name = "ArrowBack",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).apply {
            path(
                fill = SolidColor(Color.White),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4.0f,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(20.0f, 11.0f)
                horizontalLineTo(7.83f)
                lineTo(13.42f, 5.41f)
                lineTo(12.0f, 4.0f)
                lineTo(4.0f, 12.0f)
                lineTo(12.0f, 20.0f)
                lineTo(13.41f, 18.59f)
                lineTo(7.83f, 13.0f)
                horizontalLineTo(20.0f)
                verticalLineTo(11.0f)
                close()
            }
        }.build()
}
