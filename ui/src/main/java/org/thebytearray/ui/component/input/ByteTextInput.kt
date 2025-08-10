package org.thebytearray.ui.component.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.thebytearray.ui.theme.Neutral200
import org.thebytearray.ui.theme.Neutral300
import org.thebytearray.ui.theme.Neutral400
import org.thebytearray.ui.theme.Neutral50
import org.thebytearray.ui.theme.Neutral600
import org.thebytearray.ui.theme.Neutral900

@Composable
fun ByteTextField(
    value: String,
    onValueChange: (String) -> Unit,

    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: androidx.compose.ui.text.input.VisualTransformation = androidx.compose.ui.text.input.VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: androidx.compose.foundation.interaction.MutableInteractionSource? = null,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(6.dp),
    colors: androidx.compose.material3.TextFieldColors? = null,
    isDarkMode: Boolean = false,
) {
    val defaultColors = TextFieldDefaults.colors(
        focusedTextColor = if (isDarkMode) Neutral50 else Neutral900,
        unfocusedTextColor = if (isDarkMode) Neutral50 else Neutral900,
        disabledTextColor = if (isDarkMode) Neutral600 else Neutral400,
        errorTextColor = Color.Red,

        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,

        cursorColor = if (isDarkMode) Neutral50 else Neutral900,
        errorCursorColor = Color.Red,

        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,

        focusedLabelColor = if (isDarkMode) Neutral50 else Neutral900,
        unfocusedLabelColor = if (isDarkMode) Neutral600 else Neutral400,
        disabledLabelColor = if (isDarkMode) Neutral600 else Neutral400,
        errorLabelColor = Color.Red,

        focusedPlaceholderColor = if (isDarkMode) Neutral600 else Neutral400,
        unfocusedPlaceholderColor = if (isDarkMode) Neutral600 else Neutral400,
        disabledPlaceholderColor = if (isDarkMode) Neutral600 else Neutral400,
        errorPlaceholderColor = Color.Red
    )

    val borderColor = when {
        isError -> Color.Red
        enabled -> if (isDarkMode) Neutral400 else Neutral300
        else -> if (isDarkMode) Neutral600 else Neutral200
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isDarkMode) Neutral900 else Neutral50, shape = shape
            )
            .border(
                width = 1.dp, color = borderColor, shape = shape
            ),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle.copy(color = if (isDarkMode) Neutral50 else Neutral900),
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors ?: defaultColors
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewByteTextFieldLight() {
    var text by remember { mutableStateOf("") }
    ByteTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Enter text...") },
        isDarkMode = false,
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
fun PreviewByteTextFieldDark() {
    var text by remember { mutableStateOf("") }
    ByteTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Enter text...") },
        isDarkMode = true,
        modifier = Modifier.padding(16.dp)
    )
}
