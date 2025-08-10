package org.thebytearray.ui.component.radio

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ByteRadioGroup(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = isSystemInDarkTheme(),
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        options.forEach { option ->
            ByteRadioButton(
                label = option,
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) },
                isDarkMode = isDarkMode
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewByteRadioGroupLight() {
    ByteRadioGroup(options = listOf("Default", "Comfortable", "Compact"), selectedOption = "Comfortable", onOptionSelected = {})
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, backgroundColor = 0x000000)
@Composable
private fun PreviewByteRadioGroupDark() {
    ByteRadioGroup(options = listOf("Default", "Comfortable", "Compact"), selectedOption = "Compact", onOptionSelected = {}, isDarkMode = true)
}


