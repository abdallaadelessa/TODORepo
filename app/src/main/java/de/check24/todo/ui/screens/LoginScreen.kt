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
fun LoginScreen() {
    Column {

        TopAppBar(
            title = { Text(text = "Todo Login") }
        )

        HeightSpacer(height = 16.dp)

        Container(modifier = Gravity.Center wraps ExpandedHeight) {
            Button(
                modifier = Gravity.Center,
                text = "Login",
                onClick = { TodoApp.navigateTo(TodoApp.Screen.Overview) },
                style = TextButtonStyle()
            )
        }

    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}
