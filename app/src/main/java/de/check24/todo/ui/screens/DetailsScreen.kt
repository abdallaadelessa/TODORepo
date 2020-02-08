package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.layout.*
import androidx.ui.material.TopAppBar
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import de.check24.todo.R
import de.check24.todo.pojo.Todo
import de.check24.todo.ui.utils.imageUrl
import java.util.*

@Composable
fun DetailsScreen(todo: Todo) {

    val image = +imageUrl(todo.imageUrl) ?: +imageResource(R.drawable.header)

    Column {
        TopAppBar(
            title = { Text(text = "Todo Details") }
        )

        Container(width = 40.dp, height = 120.dp, modifier = Gravity.Center wraps ExpandedWidth) {
            DrawImage(image)
        }

        Text(text = todo.title)
        Text(text = todo.description)
        Text(text = todo.crationtime.toString())
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {

    val todo = Todo(
        "My Title",
        "Description",
        "https://www.welt.de/img/bildergalerien/mobile197770285/5051626737-ci23x11-w1680/Forscher-entdecken-Planetentrio.jpg",
        Date()
    )

    DetailsScreen(todo)
}
