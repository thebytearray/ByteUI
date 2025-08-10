package org.thebytearray.ui.component.toggle

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.thebytearray.ui.theme.Neutral200
import org.thebytearray.ui.theme.Neutral300
import org.thebytearray.ui.theme.Neutral400
import org.thebytearray.ui.theme.Neutral50
import org.thebytearray.ui.theme.Neutral600
import org.thebytearray.ui.theme.Neutral700
import org.thebytearray.ui.theme.Neutral900
import org.thebytearray.ui.theme.Neutral950

@Composable
fun ByteSwitch(
    isOn: Boolean,
    onToggle: (Boolean) -> Unit,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val density = LocalDensity.current

    var isDragging by remember { mutableStateOf(false) }
    var dragOffset by remember { mutableFloatStateOf(0f) }

    val trackWidth = 120.dp
    val trackHeight = 64.dp
    val thumbSize = 52.dp
    val padding = 6.dp

    val maxOffset = with(density) { (trackWidth - thumbSize - padding * 2).toPx() }
    val threshold = maxOffset * 0.5f

    val animatedOffset by animateFloatAsState(
        targetValue = if (isDragging) {
            dragOffset.coerceIn(0f, maxOffset)
        } else if (isOn) {
            maxOffset
        } else {
            0f
        }, animationSpec = tween(200), label = "switchOffset"
    )

    val trackOnColor = if (isDarkMode) Neutral700 else Neutral200
    val trackOffColor = if (isDarkMode) Neutral900 else Neutral300
    val thumbOnColor = if (isDarkMode) Neutral50 else Neutral900
    val thumbOffColor = if (isDarkMode) Neutral600 else Neutral400
    val shadowColor = if (isDarkMode) Neutral950 else Neutral200
    val rippleColor = if (isDarkMode) Neutral400 else Neutral600

    val currentTrackColor = if (isOn) trackOnColor else trackOffColor
    val currentThumbColor = if (isOn) thumbOnColor else thumbOffColor

    fun handleToggle(newState: Boolean) {
        onToggle(newState)
    }

    Box(
        modifier = modifier
            .size(trackWidth, trackHeight)
            .shadow(
                elevation = if (enabled) 4.dp else 0.dp,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(trackHeight / 2),
                ambientColor = shadowColor,
                spotColor = shadowColor
            )
            .clickable(
                enabled = enabled,
                role = Role.Switch,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                handleToggle(!isOn)
            }
            .pointerInput(enabled) {
                if (enabled) {
                    detectDragGestures(onDragStart = {
                        isDragging = true
                        dragOffset = animatedOffset
                    }, onDragEnd = {
                        val shouldBeOn = dragOffset > threshold
                        if (shouldBeOn != isOn) {
                            handleToggle(shouldBeOn)
                        } else {
                            onToggle(shouldBeOn)
                        }
                        isDragging = false
                    }) { _, dragAmount ->
                        dragOffset = (dragOffset + dragAmount.x).coerceIn(0f, maxOffset)
                    }
                }
            }, contentAlignment = Alignment.CenterStart
    ) {
        Canvas(
            modifier = Modifier.size(trackWidth, trackHeight)
        ) {
            drawRoundedTrack(
                color = currentTrackColor.copy(alpha = if (enabled) 1f else 0.5f), size = size
            )
        }

        Box(
            modifier = Modifier
                .offset(x = with(density) { (animatedOffset + padding.toPx()).toDp() })
                .size(thumbSize)
                .shadow(
                    elevation = if (enabled) 6.dp else 2.dp,
                    shape = CircleShape,
                    ambientColor = shadowColor,
                    spotColor = shadowColor
                )
                .clip(CircleShape)
                .background(currentThumbColor.copy(alpha = if (enabled) 1f else 0.5f))
                .clickable(
                    enabled = enabled,
                    role = Role.Switch,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(
                        bounded = true, radius = thumbSize / 2, color = rippleColor
                    )
                ) {
                    handleToggle(!isOn)
                })
    }
}

private fun DrawScope.drawRoundedTrack(
    color: Color, size: Size
) {
    val cornerRadius = size.height / 2
    drawRoundRect(
        color = color,
        topLeft = Offset.Zero,
        size = size,
        cornerRadius = CornerRadius(cornerRadius, cornerRadius)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewByteSwitchLight() {
    var isOn by remember { mutableStateOf(true) }
    ByteSwitch(
        isOn = isOn, onToggle = { isOn = it }, isDarkMode = false, modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewByteSwitchDark() {
    var isOn by remember { mutableStateOf(false) }
    ByteSwitch(
        isOn = isOn, onToggle = { isOn = it }, isDarkMode = true, modifier = Modifier
    )
}
