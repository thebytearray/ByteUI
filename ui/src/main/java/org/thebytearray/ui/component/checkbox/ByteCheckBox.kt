package org.thebytearray.ui.component.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.isSystemInDarkTheme
import org.thebytearray.ui.theme.*

@Composable
fun ByteCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    shape: RoundedCornerShape = RoundedCornerShape(4.dp),
    checkmarkIcon: ImageVector = Icons.Filled.Check
) {
    val isDarkMode = isSystemInDarkTheme()

    val checkedContainerColor = if (isDarkMode) Neutral50 else Neutral900
    val checkedIconColor = if (isDarkMode) Neutral700 else Neutral100
    val uncheckedBorderColor = if (isDarkMode) Neutral600 else Neutral300
    val uncheckedContainerColor = if (isDarkMode) Neutral700 else Neutral100
    val disabledBorderColor = if (isDarkMode) Neutral700 else Neutral200

    val borderColor = when {
        !enabled -> disabledBorderColor
        checked -> checkedContainerColor
        else -> uncheckedBorderColor
    }

    val backgroundColor = if (checked) checkedContainerColor else uncheckedContainerColor
    val overallAlpha = if (enabled) 1f else 0.5f

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(enabled = enabled) { onCheckedChange(!checked) }) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(color = backgroundColor.copy(alpha = overallAlpha), shape = shape)
                .border(
                    width = 1.dp, color = borderColor.copy(alpha = overallAlpha), shape = shape
                ), contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = checkmarkIcon,
                    contentDescription = null,
                    tint = checkedIconColor.copy(alpha = overallAlpha),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        if (label != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                color = (if (isDarkMode) Neutral50 else Neutral900).copy(alpha = overallAlpha),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewByteCheckBoxLight() {
    var checked by remember { mutableStateOf(false) }
    ByteCheckBox(
        checked = checked,
        onCheckedChange = { checked = it },
        label = "Accept terms and conditions",
        modifier = Modifier.padding(16.dp),
        enabled = true
    )
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0x000000
)
@Composable
fun PreviewByteCheckBoxDark() {
    var checked by remember { mutableStateOf(false) }
    ByteCheckBox(
        checked = checked,
        onCheckedChange = { checked = it },
        label = "Accept terms and conditions",
        modifier = Modifier.padding(16.dp),
        enabled = true
    )
}
