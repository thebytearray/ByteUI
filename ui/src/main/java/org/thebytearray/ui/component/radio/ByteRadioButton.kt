package org.thebytearray.ui.component.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.thebytearray.ui.theme.Neutral100
import org.thebytearray.ui.theme.Neutral300
import org.thebytearray.ui.theme.Neutral50
import org.thebytearray.ui.theme.Neutral600
import org.thebytearray.ui.theme.Neutral700
import org.thebytearray.ui.theme.Neutral800
import org.thebytearray.ui.theme.Neutral900

@Composable
fun ByteRadioButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = isSystemInDarkTheme(),
) {
    val trackColor = if (isDarkMode) Neutral800 else Neutral100
    val borderColor = if (isDarkMode) Neutral600 else Neutral300
    val dotColor = if (isDarkMode) Neutral700 else Neutral100
    val selectedFill = if (isDarkMode) Neutral50 else Neutral900
    val labelColor = if (isDarkMode) Neutral50 else Neutral900

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(color = if (selected) selectedFill else trackColor)
                .border(width = 1.dp, color = if (selected) selectedFill else borderColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(dotColor)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, color = labelColor, style = MaterialTheme.typography.bodyMedium)
    }
}


