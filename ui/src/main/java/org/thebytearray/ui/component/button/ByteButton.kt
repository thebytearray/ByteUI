package org.thebytearray.ui.component.button

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.thebytearray.ui.component.text.ByteText
import org.thebytearray.ui.theme.Neutral200
import org.thebytearray.ui.theme.Neutral50
import org.thebytearray.ui.theme.Neutral600
import org.thebytearray.ui.theme.Neutral700
import org.thebytearray.ui.theme.Neutral900
import org.thebytearray.ui.theme.Typography


@Composable
fun ByteOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(size = 6.dp),
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        containerColor = Color.Transparent,
        contentColor = if (isSystemInDarkTheme()) Neutral200 else Neutral700
    ),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = BorderStroke(
        width = 1.dp, color = if (isSystemInDarkTheme()) Neutral600 else Neutral200
    ),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(
            minWidth = ByteButtonDefaults.MinWidth, minHeight = ByteButtonDefaults.MinHeight
        ),
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}


@Composable
fun ByteFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(size = 6.dp),
    colors: ButtonColors = ButtonDefaults.filledTonalButtonColors(
        containerColor = if (isSystemInDarkTheme()) Neutral50 else Neutral900,
        contentColor = if (isSystemInDarkTheme()) Neutral900 else Neutral50
    ),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(
            minWidth = ByteButtonDefaults.MinWidth, minHeight = ByteButtonDefaults.MinHeight
        ),
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}


@Preview(
    name = "Dark Outlined", showBackground = true, backgroundColor = 0x00000000,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PreviewByteDarkOutlinedButton() {
    ByteOutlinedButton(onClick = {}) {
        ByteText(text = "Cancel", style = Typography.bodyMedium)
    }
}


@Preview(name = "Light Outlined", showBackground = true, backgroundColor = 0x00000000)
@Composable
private fun PreviewByteLightOutlinedButton() {
    ByteOutlinedButton(onClick = {}) {
        ByteText(text = "Cancel", style = Typography.bodyMedium)
    }
}


@Preview(name = "Light Filled", showBackground = true, backgroundColor = 0x00000000)
@Composable
private fun PreviewByteLightFilledButton() {
    ByteFilledButton(onClick = {}) {
        ByteText(text = "Continue", style = Typography.bodyMedium)
    }
}

@Preview(
    name = "Dark Filled",
    showBackground = true,
    backgroundColor = 0x00000000,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PreviewByteDarkFilledButton() {
    ByteFilledButton(onClick = {}) {
        ByteText(text = "Continue", style = Typography.bodyMedium)
    }
}


object ByteButtonDefaults {
    /**
     * The default min width applied for all buttons. Note that you can override it by applying
     * Modifier.widthIn directly on the button composable.
     */
    val MinWidth = 77.dp

    /**
     * The default min height applied for all buttons. Note that you can override it by applying
     * Modifier.heightIn directly on the button composable.
     */
    val MinHeight = 40.dp
}