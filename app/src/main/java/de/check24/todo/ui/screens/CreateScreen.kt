package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Column
import androidx.ui.layout.HeightSpacer
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import de.check24.todo.R
import de.check24.todo.ui.TodoApp
import de.check24.todo.ui.utils.VectorImageButton

@Composable
fun CreateScreen() {
    Column {

        TopAppBar(
            title = { Text(text = "Todo Create") },
            navigationIcon = {
                VectorImageButton(R.drawable.ic_back_arrow) {
                   TodoApp.navigateTo(TodoApp.Screen.Overview)
                }
            }
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
