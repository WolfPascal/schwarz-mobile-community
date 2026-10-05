package com.schwarz_digits.mobilecommunity.core.ui.network

import com.schwarz_digits.mobilecommunity.core.ui.mock.MockDataProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlin.random.Random

/**
 * Provides configured Ktor [HttpClient] instances backed by [MockEngine].
 * Simulates real-world network latency (1-3s), JSON content negotiation,
 * and responses using local mock files.
 */
object NetworkClientProvider {
    val jsonConfig =
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
        }

    val defaultClient: HttpClient by lazy {
        createClient()
    }

    fun createClient(
        minDelayMs: Long = 1000L,
        maxDelayMs: Long = 3000L,
        simulateError: Boolean = false,
        simulateEmpty: Boolean = false,
        mockDataProvider: MockDataProvider = MockDataProvider,
    ): HttpClient {
        return HttpClient(MockEngine) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(jsonConfig)
            }
            engine {
                addHandler { request ->
                    val urlPath = request.url.encodedPath

                    if (urlPath.contains("/v1/images/")) {
                        // User requirement: 0-2 seconds delay per image
                        val imageDelay = Random.nextLong(0L, 2001L)
                        delay(imageDelay)

                        if (simulateError) {
                            return@addHandler respond(
                                content = """{"error": "Internal Server Error", "code": 500}""",
                                status = HttpStatusCode.InternalServerError,
                                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                            )
                        }

                        val imageName = urlPath.substringAfterLast("/")
                        val imageBytes = runCatching { mockDataProvider.getImageBytes(imageName) }.getOrNull()
                        return@addHandler if (imageBytes != null) {
                            val contentType =
                                if (imageName.endsWith(".png", ignoreCase = true)) {
                                    ContentType.Image.PNG
                                } else {
                                    ContentType.Image.JPEG
                                }
                            respond(
                                content = imageBytes,
                                status = HttpStatusCode.OK,
                                headers = headersOf(HttpHeaders.ContentType, contentType.toString()),
                            )
                        } else {
                            respond(
                                content = """{"error": "Image not found", "image": "$imageName"}""",
                                status = HttpStatusCode.NotFound,
                                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                            )
                        }
                    }

                    // Simulate network latency between minDelayMs and maxDelayMs
                    val simulatedDelay =
                        if (maxDelayMs > minDelayMs) {
                            Random.nextLong(minDelayMs, maxDelayMs + 1)
                        } else {
                            minDelayMs
                        }
                    delay(simulatedDelay)

                    if (simulateError) {
                        return@addHandler respond(
                            content = """{"error": "Internal Server Error", "code": 500}""",
                            status = HttpStatusCode.InternalServerError,
                            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                        )
                    }

                    if (simulateEmpty) {
                        return@addHandler respond(
                            content = "[]",
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                        )
                    }

                    when {
                        urlPath.endsWith("/v1/news") || urlPath.endsWith("/news") -> {
                            val json = mockDataProvider.getNewsJson()
                            respond(
                                content = json,
                                status = HttpStatusCode.OK,
                                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                            )
                        }
                        urlPath.endsWith("/v1/meetings") || urlPath.endsWith("/meetings") -> {
                            val json = mockDataProvider.getMeetingsJson()
                            respond(
                                content = json,
                                status = HttpStatusCode.OK,
                                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                            )
                        }
                        else -> {
                            respond(
                                content = """{"error": "Resource Not Found", "path": "$urlPath"}""",
                                status = HttpStatusCode.NotFound,
                                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                            )
                        }
                    }
                }
            }
        }
    }
}
