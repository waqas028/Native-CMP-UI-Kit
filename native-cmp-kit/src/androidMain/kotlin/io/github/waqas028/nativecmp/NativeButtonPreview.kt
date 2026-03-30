package io.github.waqas028.nativecmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape

@Preview(showBackground = true)
@Composable
fun NativeButtonPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        NativeButton(
            text = "Filled Button",
            onClick = {}
        )
        NativeTonalButton(
            text = "Tonal Button",
            onClick = {},
            modifier = Modifier.padding(top = 8.dp)
        )
        NativeOutlinedButton(
            text = "Outlined Button",
            onClick = {},
            modifier = Modifier.padding(top = 8.dp)
        )
        NativeTextButton(
            text = "Text Button",
            onClick = {},
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        NativeButton(
            text = "Custom Colors",
            onClick = {},
            containerColor = Color.Red,
            contentColor = Color.White
        )
        NativeButton(
            text = "Rounded",
            onClick = {},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
