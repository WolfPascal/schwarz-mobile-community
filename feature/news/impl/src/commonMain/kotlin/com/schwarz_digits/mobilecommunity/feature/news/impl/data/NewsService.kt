package com.schwarz_digits.mobilecommunity.feature.news.impl.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Service responsible for fetching news from the backend endpoint via Ktor.
 */
class NewsService(
    private val client: HttpClient,
) {
    suspend fun fetchNews(): List<NewsArticleDto> = client.get("https://api.schwarz-digits.local/v1/news").body()
}
