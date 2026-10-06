# Technical Architecture: Network & Mock Simulation

## 1. Network Strategy: Ktor with MockEngine

To avoid reliance on third-party live servers during workshops while preserving realistic HTTP patterns, we use the official **Ktor HTTP Client** powered by **`MockEngine`**.

### Advantages:
- Genuine HTTP request/response cycle (headers, status codes, JSON content negotiation).
- Simulates network latency (e.g., 600ms delay) to realistically display loading spinners.
- Easy toggling between Success, Empty, and Error scenarios for testing and demonstration.

---

## 2. Mock Engine Configuration

Located in `:core:network`:

```kotlin
class MockNetworkEngineProvider {
    fun createMockEngine(): HttpClientEngine {
        return MockEngine { request ->
            val path = request.url.encodedPath
            when {
                path.endsWith("/api/v1/news") -> {
                    respond(
                        content = NewsMockJson.content,
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                path.endsWith("/api/v1/meetings/upcoming") -> {
                    respond(
                        content = MeetingsMockJson.upcomingContent,
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                else -> {
                    respond(
                        content = """{"error": "Endpoint not found"}""",
                        status = HttpStatusCode.NotFound,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
            }
        }
    }
}
```

---

## 3. Data Transfer Objects (DTOs) & Serialization

DTOs live in `:core:network` and use `kotlinx.serialization`:

```kotlin
@Serializable
data class NewsArticleDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("summary") val summary: String,
    @SerialName("content") val content: String,
    @SerialName("author") val author: String,
    @SerialName("published_at") val publishedAt: String,
    @SerialName("read_time_minutes") val readTimeMinutes: Int,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("tags") val tags: List<String> = emptyList()
)
```

Mapping from `NewsArticleDto` to domain model `NewsArticle` is handled exclusively within `:core:data`.
