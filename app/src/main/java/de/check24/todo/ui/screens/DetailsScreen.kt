package de.check24.todo.ui.screens

import androidx.compose.*
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.graphics.Image
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.TextButtonStyle
import androidx.ui.material.TopAppBar
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import coil.Coil
import coil.api.newGetBuilder
import coil.request.GetRequest
import de.check24.todo.R
import de.check24.todo.pojo.Todo
import de.check24.todo.ui.TodoApp
import de.check24.todo.util.RemoteImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun DetailsScreen() {

    val todo = Todo(
        "My Title",
        "Description",
        "https://www.welt.de/img/bildergalerien/mobile197770285/5051626737-ci23x11-w1680/Forscher-entdecken-Planetentrio.jpg",
        Date()
    )

    val image = +image(todo.imageUrl) ?: +imageResource(R.drawable.header)

    Column {

        TopAppBar(
            title = { Text(text = "Todo Details") }
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


/**
 * A simple [image] effect, which loads [data] with the default options.
 */
@CheckResult(suggest = "+")
fun image(data: Any) = effectOf<Image?> {
    // Positionally memoize the request creation so
    // it will only be recreated if data changes.
    val request = +memo(data) {
        Coil.loader().newGetBuilder().data(data).build()
    }
    +image(request)
}

/**
 * A configurable [image] effect, which accepts a [request] value object.
 */
@CheckResult(suggest = "+")
fun image(request: GetRequest) = effectOf<Image?> {
    val image = +state<Image?> { null }

    // Execute the following code whenever the request changes.
    +onCommit(request) {
        val job = CoroutineScope(Dispatchers.Main.immediate).launch {
            // Start loading the image and await the result.
            val drawable = Coil.loader().get(request)
            image.value = RemoteImage(drawable.toBitmap())
        }

        // Cancel the request if the input to onCommit changes or
        // the Composition is removed from the composition tree.
        onDispose { job.cancel() }
    }

    // Emit a null Image to start with.
    image.value
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    DetailsScreen()
}
