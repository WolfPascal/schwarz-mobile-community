package com.schwarz_digits.mobilecommunity.feature.news.impl

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schwarz_digits.mobilecommunity.feature.news.api.NewsFeatureEntry

/**
 * Concrete implementation of [NewsFeatureEntry].
 */
class NewsFeatureEntryImpl : NewsFeatureEntry {
    @Composable
    override fun NewsContent(
        contentPadding: PaddingValues,
        modifier: Modifier,
    ) {
        NewsScreen(
            contentPadding = contentPadding,
            modifier = modifier,
        )
    }

    @Composable
    override fun NewsContent(contentPadding: PaddingValues) {
        NewsContent(contentPadding = contentPadding, modifier = Modifier)
    }

    @Composable
    override fun NewsContent() {
        NewsContent(contentPadding = PaddingValues(0.dp), modifier = Modifier)
    }
}
