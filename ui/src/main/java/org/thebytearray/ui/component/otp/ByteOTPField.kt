package org.thebytearray.ui.component.otp

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.thebytearray.ui.theme.Neutral100
import org.thebytearray.ui.theme.Neutral200
import org.thebytearray.ui.theme.Neutral300
import org.thebytearray.ui.theme.Neutral400
import org.thebytearray.ui.theme.Neutral50
import org.thebytearray.ui.theme.Neutral600
import org.thebytearray.ui.theme.Neutral900

@Composable
fun ByteOTPField(
    length: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = isSystemInDarkTheme(),
) {
    val focusRequesters = remember(length) { List(length) { FocusRequester() } }
    val chars = remember(length) { mutableStateListOf<Char?>(*Array(length) { null }) }

    LaunchedEffect(value) {
        value.take(length).forEachIndexed { index, c -> chars[index] = c }
        for (i in value.length until length) chars[i] = null
    }

    val container = if (isDarkMode) Neutral900 else Neutral50
    val border = if (isDarkMode) Neutral400 else Neutral300
    val textColor = if (isDarkMode) Neutral50 else Neutral900
    val placeholder = if (isDarkMode) Neutral600 else Neutral400

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(length) { index ->
            val focusRequester = focusRequesters[index]
            val char = chars[index]
            BasicTextField(
                value = char?.toString() ?: "",
                onValueChange = { input ->
                    if (input.isEmpty()) {
                        chars[index] = null
                        val newValue = buildString { chars.forEach { if (it != null) append(it) } }
                        onValueChange(newValue)
                        if (index > 0) focusRequesters[index - 1].requestFocus()
                    } else {
                        val c = input.last()
                        if (c.isLetterOrDigit()) {
                            chars[index] = c
                            val newValue = buildString { chars.forEach { if (it != null) append(it) } }
                            onValueChange(newValue.take(length))
                            if (index < length - 1) focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp, color = textColor, textAlign = TextAlign.Center),
                modifier = Modifier
                    .size(44.dp)
                    .background(container, RoundedCornerShape(6.dp))
                    .border(1.dp, border, RoundedCornerShape(6.dp))
                    .focusRequester(focusRequester)
                    .focusable(true),
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Ascii),
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                decorationBox = { inner ->
                    if (char == null) {
                        Text(text = "•", color = placeholder, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 8.dp))
                    }
                    inner()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewByteOTPFieldLight() {
    var current by remember { mutableStateOf("") }
    ByteOTPField(length = 6, value = current, onValueChange = { current = it }, isDarkMode = false)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, backgroundColor = 0x000000)
@Composable
private fun PreviewByteOTPFieldDark() {
    var current by remember { mutableStateOf("") }
    ByteOTPField(length = 6, value = current, onValueChange = { current = it }, isDarkMode = true)
}


