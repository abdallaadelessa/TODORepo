package de.check24.todo.ui.screens

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.withOpacity
import androidx.ui.res.imageResource
import androidx.ui.text.font.FontWeight.Companion.Bold
import androidx.ui.tooling.preview.Preview
import de.check24.todo.R
import de.check24.todo.pojo.Todo
import de.check24.todo.ui.utils.VectorImageButton
import de.check24.todo.ui.utils.imageUrl
import java.util.*

@Composable
fun DetailsScreen(todo: Todo) {

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

        Container(width = 40.dp, height = 120.dp, modifier = Gravity.Center wraps ExpandedWidth) {
            DrawImage(image)
        }

        Column(modifier = Spacing(left = 10.dp, right = 10.dp)) {
            HeightSpacer(24.dp)
            Text(
                text = todo.title,
                style = (+MaterialTheme.typography()).h6
            )
            HeightSpacer(24.dp)
            Text(text = todo.description)
            HeightSpacer(24.dp)
            Text(text = todo.creationTime.toString())
        }
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
