package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.core.dp
import androidx.ui.foundation.shape.border.Border
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.TopAppBar
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import de.check24.todo.DataProvider
import de.check24.todo.R
import de.check24.todo.pojo.Todo
import de.check24.todo.ui.TodoApp
import de.check24.todo.ui.utils.VectorImageButton

@Model
class TextState {
    var text: String = ""
    var error: String = ""
}

@Composable
fun CreateScreen() {

    val titleState = TextState()
    val descriptionState = TextState()

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

        Column(modifier = Expanded wraps Spacing(16.dp)) {

            Column {
                Text("Title")
                HeightSpacer(height = 4.dp)
                Surface(border = Border(Color.Gray, 0.25.dp), color = Color.Transparent) {
                    Padding(padding = 8.dp) {
                        TextField(
                            value = titleState.text,
                            onValueChange = {
                                titleState.text = it
                                titleState.error = ""
                            }
                        )
                    }
                }
                if (titleState.error.isNotEmpty()) {
                    Text(
                        text = titleState.error,
                        style = TextStyle(color = Color.Red)
                    )
                }
            }

            HeightSpacer(height = 32.dp)

            Column {
                Text("Description")
                HeightSpacer(height = 4.dp)
                Surface(border = Border(Color.Gray, 0.25.dp), color = Color.Transparent) {
                    Padding(padding = 8.dp) {
                        TextField(
                            value = descriptionState.text,
                            onValueChange = {
                                descriptionState.text = it
                                descriptionState.error = ""
                            }
                        )
                    }
                }

                if (descriptionState.error.isNotEmpty()) {
                    Text(
                        text = descriptionState.error,
                        style = TextStyle(color = Color.Red)
                    )
                }
            }

            Align(alignment = Alignment.BottomCenter) {
                Button(
                    modifier = ExpandedWidth,
                    text = "Create",
                    onClick = {

                        val title = titleState.text
                        val description = descriptionState.text

                        var hasError = false

                        if (title.isEmpty()) {
                            titleState.error = "Title is empty"
                            hasError = true
                        }

                        if (description.isEmpty()) {
                            descriptionState.error = "Description is empty"
                            hasError = true
                        }

                        if (!hasError) {
                            TodoApp.navigateTo(
                                TodoApp.Screen.Details(
                                    createTodo(
                                        title,
                                        description
                                    )
                                )
                            )
                        }

                    })
            }
        }
    }

}

private fun createTodo(title: String, description: String): Todo {
    val element = Todo(
        title,
        description
    )
    DataProvider.todoList.add(
        element
    )
    return element
}

@Preview
@Composable
private fun CreateScreenPreview() {
    CreateScreen()
}
