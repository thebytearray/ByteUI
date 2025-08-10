package org.thebytearray.ui.component.snackbar

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.thebytearray.ui.component.button.ByteOutlinedButton
import org.thebytearray.ui.theme.Neutral100
import org.thebytearray.ui.theme.Neutral200
import org.thebytearray.ui.theme.Neutral50
import org.thebytearray.ui.theme.Neutral700
import org.thebytearray.ui.theme.Neutral900

data class ByteToast(
    val id: Long,
    val title: String,
    val description: String? = null,
    val actionLabel: String? = null,
    val onAction: (() -> Unit)? = null,
    val durationMs: Long = 3500
)

object ByteSnackBarHostState {
    private val toasts = mutableStateListOf<ByteToast>()

    fun show(
        title: String,
        description: String? = null,
        actionLabel: String? = null,
        onAction: (() -> Unit)? = null,
        durationMs: Long = 3500
    ) {
        val toast = ByteToast(
            id = System.currentTimeMillis(),
            title = title,
            description = description,
            actionLabel = actionLabel,
            onAction = onAction,
            durationMs = durationMs
        )
        toasts.add(toast)
    }

    fun dismiss(id: Long) {
        toasts.removeAll { it.id == id }
    }

    fun current(): List<ByteToast> = toasts
}

@Composable
fun ByteSnackBarHost(modifier: Modifier = Modifier) {
    val dark = isSystemInDarkTheme()
    val bg = if (dark) Neutral900 else Neutral50
    val fg = if (dark) Neutral50 else Neutral900

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ByteSnackBarHostState.current().forEach { toast ->
            var visible by remember(toast.id) { mutableStateOf(false) }
            LaunchedEffect(toast.id) {
                visible = true
                kotlinx.coroutines.delay(toast.durationMs)
                visible = false
                kotlinx.coroutines.delay(250)
                ByteSnackBarHostState.dismiss(toast.id)
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(150)),
                exit = fadeOut(animationSpec = tween(150))
            ) {
                Row(
                    modifier = Modifier
                        .widthIn(max = 520.dp)
                        .fillMaxWidth()
                        .background(bg, RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = toast.title, color = fg, fontWeight = FontWeight.Medium)
                        if (toast.description != null) {
                            Text(text = toast.description, color = fg.copy(alpha = 0.8f))
                        }
                    }
                    if (toast.actionLabel != null && toast.onAction != null) {
                        ByteOutlinedButton(onClick = toast.onAction) {
                            Text(text = toast.actionLabel, color = fg)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewByteSnackBarLight() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ByteSnackBarHost(modifier = Modifier.align(Alignment.TopCenter))
        LaunchedEffect(Unit) {
            ByteSnackBarHostState.show(
                title = "Event has been created",
                description = "Sunday, December 03, 2023 at 9:00 AM",
                actionLabel = "Undo",
                onAction = {}
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, backgroundColor = 0x000000)
@Composable
private fun PreviewByteSnackBarDark() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ByteSnackBarHost(modifier = Modifier.align(Alignment.TopCenter))
        LaunchedEffect(Unit) {
            ByteSnackBarHostState.show(
                title = "Event has been created",
                description = "Sunday, December 03, 2023 at 9:00 AM",
                actionLabel = "Undo",
                onAction = {}
            )
        }
    }
}


