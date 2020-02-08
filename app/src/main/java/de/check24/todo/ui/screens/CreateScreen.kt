package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Column
import androidx.ui.layout.HeightSpacer
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview

@Composable
fun CreateScreen() {
    Column {

        TopAppBar(
            title = { Text(text = "Todo Create") }
        )

        HeightSpacer(height = 16.dp)

        Text(
            text = "Create Screen"
        )

    }
}

@Preview
@Composable
private fun CreateScreenPreview() {
    CreateScreen()
}
