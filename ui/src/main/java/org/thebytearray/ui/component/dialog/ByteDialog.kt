package org.thebytearray.ui.component.dialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.thebytearray.ui.component.button.ByteFilledButton
import org.thebytearray.ui.component.button.ByteOutlinedButton
import org.thebytearray.ui.component.card.ByteCard
import org.thebytearray.ui.component.text.ByteText

@Composable
fun ByteDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
        content = { ByteCard(modifier = Modifier.wrapContentSize()) { content() } })
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewByteDialogWithButtons() {
    ByteDialog(onDismissRequest = {}) {
        Column(modifier = Modifier.padding(16.dp)) {
            ByteText(
                text = "Are you sure you want to continue?",
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ByteOutlinedButton(
                    onClick = { /* Handle Cancel */ }, modifier = Modifier.weight(1f)
                ) {
                    ByteText(text = "Cancel")
                }
                ByteFilledButton(
                    onClick = { /* Handle Continue */ }, modifier = Modifier.weight(1f)
                ) {
                    ByteText(text = "Continue")
                }
            }
        }
    }
}
