package com.schwarz_digits.mobilecommunity.feature.meetings.api

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Public entry contract for the Meetings feature.
 * The application shell and other orchestrators interact with the Meetings screen
 * exclusively via this contract interface, decoupling caller from feature internals.
 */
interface MeetingsFeatureEntry {
    @Composable
    fun MeetingsContent(
        contentPadding: PaddingValues,
        modifier: Modifier,
    )

    @Composable
    fun MeetingsContent(contentPadding: PaddingValues)

    @Composable
    fun MeetingsContent()
}
