package org.thebytearray.ui.component.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.thebytearray.ui.component.button.ByteFilledButton
import org.thebytearray.ui.component.button.ByteOutlinedButton
import org.thebytearray.ui.component.card.ByteCard
import org.thebytearray.ui.component.text.ByteText

@Composable
fun ByteAlertDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    confirmButtonText: String = "OK",
    onConfirm: () -> Unit,
    dismissButtonText: String? = null,
    onDismiss: (() -> Unit)? = null,
    properties: DialogProperties = DialogProperties(),
) {
    Dialog(
        onDismissRequest = onDismissRequest, properties = properties
    ) {
        ByteCard(
            modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (title != null) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (icon != null) {
                            androidx.compose.material3.Icon(
                                painter = icon,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                        title()
                    }
                }

                if (text != null) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        text()
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (dismissButtonText != null && onDismiss != null) {
                        ByteOutlinedButton(
                            onClick = onDismiss, modifier = Modifier.weight(1f)
                        ) {
                            ByteText(text = dismissButtonText)
                        }
                    }
                    ByteFilledButton(
                        onClick = onConfirm, modifier = Modifier.weight(1f)
                    ) {
                        ByteText(text = confirmButtonText)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewByteAlertDialogLightWithIcon() {
    val warningIcon: Painter = rememberVectorPainter(Icons.Default.Warning)
    ByteAlertDialog(
        onDismissRequest = {},
        icon = warningIcon,
        title = { ByteText(text = "Delete file?") },
        text = { ByteText(text = "Are you sure you want to delete this file? This action cannot be undone.") },
        confirmButtonText = "Delete",
        onConfirm = {},
        dismissButtonText = "Cancel",
        onDismiss = {})
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewByteAlertDialogDarkWithIcon() {
    val warningIcon: Painter = rememberVectorPainter(Icons.Default.Warning)
    ByteAlertDialog(
        onDismissRequest = {},
        icon = warningIcon,
        title = { ByteText(text = "Delete file?") },
        text = { ByteText(text = "Are you sure you want to delete this file? This action cannot be undone.") },
        confirmButtonText = "Delete",
        onConfirm = {},
        dismissButtonText = "Cancel",
        onDismiss = {})
}