package com.schwarz_digits.mobilecommunity.feature.news.impl.data

import com.schwarz_digits.mobilecommunity.core.model.NewsArticle

/**
 * Repository for accessing community news articles, mapping network DTOs to domain entities.
 */
class NewsRepository(
    private val newsService: NewsService,
) {
    suspend fun getNewsArticles(): Result<List<NewsArticle>> =
        runCatching {
            newsService.fetchNews().map { dto ->
                NewsArticle(
                    id = dto.id,
                    title = dto.title,
                    summary = dto.summary,
                    author = dto.author,
                    date = dto.date,
                    readTime = dto.readTime,
                    tag = dto.tag,
                    imageUrl = dto.imageUrl,
                )
            }
        }
}
