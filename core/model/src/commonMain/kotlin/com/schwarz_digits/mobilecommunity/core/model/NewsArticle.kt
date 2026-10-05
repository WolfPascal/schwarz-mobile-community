package com.schwarz_digits.mobilecommunity.core.model

/**
 * Domain model representing a news article for the Schwarz Mobile Community.
 */
data class NewsArticle(
    val id: String,
    val title: String,
    val summary: String,
    val author: String,
    val date: String,
    val readTime: String,
    val tag: String,
    val imageUrl: String? = null,
)
