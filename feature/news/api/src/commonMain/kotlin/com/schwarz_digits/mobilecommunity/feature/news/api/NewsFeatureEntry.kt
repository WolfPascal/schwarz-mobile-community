package com.schwarz_digits.mobilecommunity.feature.news.api

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Public entry contract for the News feature.
 * The application shell and other orchestrators interact with the News screen
 * exclusively via this contract interface, decoupling caller from feature internals.
 */
interface NewsFeatureEntry {
    @Composable
    fun NewsContent(
        contentPadding: PaddingValues,
        modifier: Modifier,
    )

    @Composable
    fun NewsContent(contentPadding: PaddingValues)

    @Composable
    fun NewsContent()
}
