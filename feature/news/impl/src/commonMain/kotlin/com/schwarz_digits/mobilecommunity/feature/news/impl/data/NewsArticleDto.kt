package com.schwarz_digits.mobilecommunity.feature.news.impl.data

import kotlinx.serialization.Serializable

/**
 * Data transfer object representing a news article from the backend API.
 */
@Serializable
data class NewsArticleDto(
    val id: String,
    val title: String,
    val summary: String,
    val author: String,
    val date: String,
    val readTime: String,
    val tag: String,
    val imageUrl: String? = null,
)
