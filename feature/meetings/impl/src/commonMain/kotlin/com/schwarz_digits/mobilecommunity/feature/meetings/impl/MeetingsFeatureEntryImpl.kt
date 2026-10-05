package com.schwarz_digits.mobilecommunity.feature.meetings.impl

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schwarz_digits.mobilecommunity.feature.meetings.api.MeetingsFeatureEntry

/**
 * Concrete implementation of [MeetingsFeatureEntry].
 */
class MeetingsFeatureEntryImpl : MeetingsFeatureEntry {
    @Composable
    override fun MeetingsContent(
        contentPadding: PaddingValues,
        modifier: Modifier,
    ) {
        MeetingsScreen(
            contentPadding = contentPadding,
            modifier = modifier,
        )
    }

    @Composable
    override fun MeetingsContent(contentPadding: PaddingValues) {
        MeetingsContent(contentPadding = contentPadding, modifier = Modifier)
    }

    @Composable
    override fun MeetingsContent() {
        MeetingsContent(contentPadding = PaddingValues(0.dp), modifier = Modifier)
    }
}
