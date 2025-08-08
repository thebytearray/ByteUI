package org.thebytearray.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.thebytearray.app.ui.theme.ByteUITheme
import org.thebytearray.ui.component.button.ByteFilledButton
import org.thebytearray.ui.component.button.ByteOutlinedButton
import org.thebytearray.ui.component.dialog.ByteDialog
import org.thebytearray.ui.component.picker.ByteDatePicker
import org.thebytearray.ui.component.sheet.ByteBottomSheet
import org.thebytearray.ui.component.text.ByteText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ByteUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ByteUITheme {
        Greeting("Android")
    }
}