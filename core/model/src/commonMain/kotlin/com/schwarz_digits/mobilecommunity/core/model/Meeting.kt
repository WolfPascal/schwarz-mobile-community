package com.schwarz_digits.mobilecommunity.core.model

/**
 * Format of a community gathering.
 */
enum class MeetingFormat(
    val displayName: String,
) {
    HYBRID("Hybrid"),
    VIRTUAL("Virtual"),
    ONSITE("Onsite"),
}

/**
 * Domain model representing a monthly community meeting.
 */
data class Meeting(
    val id: String,
    val title: String,
    val date: String,
    val time: String,
    val format: MeetingFormat,
    val location: String,
    val speaker: String,
    val isNext: Boolean = false,
    val agendaUrl: String? = null,
)
