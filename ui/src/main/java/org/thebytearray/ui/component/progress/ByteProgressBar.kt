package org.thebytearray.ui.component.progress

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.thebytearray.ui.theme.Neutral100
import org.thebytearray.ui.theme.Neutral200
import org.thebytearray.ui.theme.Neutral200 as DarkIndicator
import org.thebytearray.ui.theme.Neutral700
import org.thebytearray.ui.theme.Neutral800
import org.thebytearray.ui.theme.Neutral900


@Composable
fun ByteProgressBar(
    value: Float,
    modifier: Modifier = Modifier,
    height: Dp = 6.dp,
) {
    val dark = isSystemInDarkTheme()

    val trackColor = if (dark) Neutral800 else Neutral100
    val indicatorColor = if (dark) DarkIndicator else Neutral900

    val clamped = value.coerceIn(0f, 100f)
    val fraction by animateFloatAsState(
        targetValue = clamped / 100f,
        animationSpec = tween(durationMillis = 350),
        label = "progressAnim"
    )

    val shape = RoundedCornerShape(999.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
            .background(trackColor)
            .semantics {
                progressBarRangeInfo = ProgressBarRangeInfo(clamped, 0f..100f)
            }) {
        if (fraction > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction)
                    .height(height)
                    .clip(shape)
                    .background(indicatorColor)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewByteProgressBarLight() {
    ByteProgressBar(value = 66f)
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PreviewByteProgressBarDark() {
    ByteProgressBar(value = 33f)
}


