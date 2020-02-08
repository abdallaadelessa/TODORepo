package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.HeightSpacer
import androidx.ui.material.TopAppBar
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import de.check24.todo.R
import de.check24.todo.pojo.Todo
import de.check24.todo.ui.utils.VectorImageButton
import de.check24.todo.ui.utils.imageUrl
import java.util.*

@Composable
fun DetailsScreen() {

    val todo = Todo(
        "My Title",
        "Description",
        "https://www.welt.de/img/bildergalerien/mobile197770285/5051626737-ci23x11-w1680/Forscher-entdecken-Planetentrio.jpg",
        Date()
    )

    val image = +imageUrl(todo.imageUrl) ?: +imageResource(R.drawable.header)

    Column {

        TopAppBar(
            title = { Text(text = "Todo Details") },
            navigationIcon = {
                VectorImageButton(R.drawable.ic_back_arrow) {
                    de.check24.todo.ui.TodoApp.navigateTo(de.check24.todo.ui.TodoApp.Screen.Overview)
                }
            }
        )

        HeightSpacer(height = 16.dp)

        Text(
            text = "Details Screen"
        )

        Container(width = 40.dp, height = 40.dp) {
            DrawImage(image)
        }

        Text {
            todo.title
        }

        Text {
            todo.description
        }

        Text {
            todo.crationtime
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    DetailsScreen()
}
