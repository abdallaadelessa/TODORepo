package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.TextButtonStyle
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import de.check24.todo.ui.TodoApp

@Composable
fun DetailsScreen() {
    Column {

        TopAppBar(
            title = { Text(text = "Todo Details") }
        )

        HeightSpacer(height = 16.dp)

        Text(
            text = "Details Screen"
        )

    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    DetailsScreen()
}
