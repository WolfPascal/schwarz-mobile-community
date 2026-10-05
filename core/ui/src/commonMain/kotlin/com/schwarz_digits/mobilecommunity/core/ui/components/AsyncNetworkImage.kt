package com.schwarz_digits.mobilecommunity.core.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.schwarz_digits.mobilecommunity.core.ui.network.NetworkClientProvider
import com.schwarz_digits.mobilecommunity.core.ui.theme.DigitsColors
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.jetbrains.compose.resources.decodeToImageBitmap

/**
 * State representing the asynchronous network image loading process.
 */
sealed interface ImageLoadState {
    data object Loading : ImageLoadState

    data class Success(
        val bitmap: ImageBitmap,
    ) : ImageLoadState

    data object Error : ImageLoadState
}

/**
 * In-memory cache for decoded [ImageBitmap] instances to prevent reloading
 * and re-triggering skeleton animations when list items are scrolled back into view.
 */
object ImageCache {
    private const val MAX_SIZE = 100
    private val cache = mutableMapOf<String, ImageBitmap>()
    private val keys = mutableListOf<String>()

    fun get(url: String): ImageBitmap? = cache[url]

    fun put(
        url: String,
        bitmap: ImageBitmap,
    ) {
        if (!cache.containsKey(url)) {
            if (keys.size >= MAX_SIZE) {
                val oldest = keys.removeFirstOrNull()
                if (oldest != null) {
                    cache.remove(oldest)
                }
            }
            keys.add(url)
        }
        cache[url] = bitmap
    }

    fun clear() {
        cache.clear()
        keys.clear()
    }
}

/**
 * Shimmer skeleton placeholder component showing an animated gradient.
 */
@Composable
fun SkeletonPlaceholder(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
) {
    val transition = rememberInfiniteTransition(label = "SkeletonTransition")
    val translateAnimation by transition.animateFloat(
        initialValue = -300f,
        targetValue = 1200f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = 1300, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Restart,
            ),
        label = "SkeletonTranslate",
    )

    val baseColor = MaterialTheme.colorScheme.surfaceVariant
    val highlightColor = DigitsColors.CyanPrimary.copy(alpha = 0.18f)

    val shimmerBrush =
        Brush.linearGradient(
            colors =
                listOf(
                    baseColor,
                    highlightColor,
                    baseColor,
                ),
            start = Offset(x = translateAnimation - 300f, y = translateAnimation - 300f),
            end = Offset(x = translateAnimation, y = translateAnimation),
        )

    Box(
        modifier =
            modifier
                .clip(shape)
                .background(shimmerBrush),
    )
}

/**
 * Asynchronously loads an image from the mock network backend with a skeleton shimmer animation.
 * Caches decoded [ImageBitmap]s in memory so scrolling back up reveals images instantly without re-animating.
 */
@Composable
fun AsyncNetworkImage(
    url: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = RoundedCornerShape(12.dp),
    client: HttpClient = remember { NetworkClientProvider.defaultClient },
) {
    val cachedBitmap = remember(url) { url?.let { ImageCache.get(it) } }
    var loadState by remember(url) {
        mutableStateOf<ImageLoadState>(
            if (cachedBitmap != null) {
                ImageLoadState.Success(cachedBitmap)
            } else {
                ImageLoadState.Loading
            },
        )
    }

    LaunchedEffect(url) {
        if (url.isNullOrBlank()) {
            loadState = ImageLoadState.Error
            return@LaunchedEffect
        }

        val existing = ImageCache.get(url)
        if (existing != null) {
            loadState = ImageLoadState.Success(existing)
            return@LaunchedEffect
        }

        loadState = ImageLoadState.Loading
        try {
            val response = client.get(url)
            val bytes = response.body<ByteArray>()
            val bitmap = bytes.decodeToImageBitmap()
            ImageCache.put(url, bitmap)
            loadState = ImageLoadState.Success(bitmap)
        } catch (e: Exception) {
            loadState = ImageLoadState.Error
        }
    }

    Box(
        modifier = modifier.clip(shape),
        contentAlignment = Alignment.Center,
    ) {
        when (val state = loadState) {
            is ImageLoadState.Loading -> {
                SkeletonPlaceholder(
                    modifier = Modifier.fillMaxSize(),
                    shape = shape,
                )
            }
            is ImageLoadState.Success -> {
                Image(
                    bitmap = state.bitmap,
                    contentDescription = contentDescription,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = contentScale,
                )
            }
            is ImageLoadState.Error -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "🖼️",
                        fontSize = 28.sp,
                    )
                }
            }
        }
    }
}
