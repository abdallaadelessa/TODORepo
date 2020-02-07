package de.check24.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.DrawImage
import androidx.ui.graphics.Image
import androidx.ui.layout.Container
import androidx.ui.res.imageResource
import coil.Coil
import coil.api.newGetBuilder
import coil.request.GetRequest
import de.check24.todo.pojo.Todo
import de.check24.todo.util.RemoteImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class OverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todo = Todo(
            "My Title",
            "Description",
            "https://www.welt.de/img/bildergalerien/mobile197770285/5051626737-ci23x11-w1680/Forscher-entdecken-Planetentrio.jpg",
            Date()
        )

        setContent {
            OverviewActivityComposable()
        }
    }
}

@Composable
fun OverviewActivityComposable() {
    val url =
        "https://www.welt.de/img/bildergalerien/mobile197770285/5051626737-ci23x11-w1680/Forscher-entdecken-Planetentrio.jpg"
    val image = +image(url) ?: +imageResource(R.drawable.header)

    Container(width = 40.dp, height = 40.dp) {
        DrawImage(image)
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