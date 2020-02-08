package de.check24.todo.ui.utils

import android.graphics.Bitmap
import androidx.annotation.CheckResult
import androidx.compose.effectOf
import androidx.compose.memo
import androidx.compose.onCommit
import androidx.compose.state
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.graphics.Image
import androidx.ui.graphics.ImageConfig
import androidx.ui.graphics.colorspace.ColorSpaces
import coil.Coil
import coil.api.newGetBuilder
import coil.request.GetRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// NOTE: copied from androidx.ui.graphics.AndroidImage
class RemoteImage(private val bitmap: Bitmap) : Image {
    override val width = bitmap.width
    override val height = bitmap.height
    override val config = ImageConfig.Argb8888
    override val colorSpace = ColorSpaces.Srgb
    override val hasAlpha = bitmap.hasAlpha()
    override val nativeImage = bitmap
    override fun prepareToDraw() = bitmap.prepareToDraw()
}


/**
 * A simple [imageUrl] effect, which loads [data] with the default options.
 */
@CheckResult(suggest = "+")
fun imageUrl(data: Any) = effectOf<Image?> {
    // Positionally memoize the request creation so
    // it will only be recreated if data changes.
    val request = +memo(data) {
        Coil.loader().newGetBuilder().data(data).build()
    }
    +imageGetRequest(request)
}

/**
 * A configurable [imageUrl] effect, which accepts a [request] value object.
 */
@CheckResult(suggest = "+")
private fun imageGetRequest(request: GetRequest) = effectOf<Image?> {
    val image = +state<Image?> { null }

    // Execute the following code whenever the request changes.
    +onCommit(request) {
        val job = CoroutineScope(Dispatchers.Main.immediate).launch {
            // Start loading the image and await the result.
            val drawable = Coil.loader().get(request)
            image.value =
                RemoteImage(drawable.toBitmap())
        }

        // Cancel the request if the input to onCommit changes or
        // the Composition is removed from the composition tree.
        onDispose { job.cancel() }
    }

    // Emit a null Image to start with.
    image.value
}
