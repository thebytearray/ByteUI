package org.thebytearray.ui.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.thebytearray.ui.theme.Neutral200
import org.thebytearray.ui.theme.Neutral50
import org.thebytearray.ui.theme.Neutral800
import org.thebytearray.ui.theme.Neutral900

@Composable
fun ByteCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = 8.dp),
    colors: CardColors = CardDefaults.cardColors(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral50,
        contentColor = if (isSystemInDarkTheme()) Neutral50 else Neutral900
    ),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = BorderStroke(
        width = 1.dp, color = if (isSystemInDarkTheme()) Neutral800 else Neutral200
    ),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        content = content
    )
}


@Preview(showBackground = true)
@Composable
private fun PreviewByteCard() {
    ByteCard(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Welcome to ByteCard",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "This is a subtitle or secondary text",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Here is some dummy content to fill the card and demonstrate how you can add multiple texts or other composables inside ByteCard.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}