package com.schwarz_digits.mobilecommunity.core.ui.mock

import com.schwarz_digits.mobilecommunity.core.ui.resources.Res

/**
 * Provider for mock JSON responses for news and meetings,
 * reading directly from local Compose Multiplatform resources.
 * If the resource cannot be loaded, the call throws an exception
 * which triggers the error state in the presentation layer.
 */
object MockDataProvider {
    suspend fun getNewsJson(): String = Res.readBytes("files/news.json").decodeToString()

    suspend fun getMeetingsJson(): String = Res.readBytes("files/meetings.json").decodeToString()

    suspend fun getImageBytes(imageName: String): ByteArray = Res.readBytes("files/images/$imageName")
}
