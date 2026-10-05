package com.schwarz_digits.mobilecommunity.feature.meetings.impl.data

import kotlinx.serialization.Serializable

/**
 * Data transfer object representing a community gathering from the backend API.
 */
@Serializable
data class MeetingDto(
    val id: String,
    val title: String,
    val date: String,
    val time: String,
    val format: String,
    val location: String,
    val speaker: String,
    val isNext: Boolean = false,
)
