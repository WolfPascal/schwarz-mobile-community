package com.schwarz_digits.mobilecommunity.feature.meetings.impl.domain

import com.schwarz_digits.mobilecommunity.core.model.Meeting
import kotlin.time.Instant

/**
 * Meetings grouped for display, relative to a given point in time.
 *
 * @property next The meeting that hasn't ended yet and starts first, or `null` if there is none.
 * @property upcoming All other meetings that haven't ended yet, earliest first.
 * @property past Meetings that have already ended, most recent first.
 */
data class MeetingSchedule(
    val next: Meeting?,
    val upcoming: List<Meeting>,
    val past: List<Meeting>,
)

/**
 * Groups [meetings] into a [MeetingSchedule] as seen at [now].
 *
 * Note: The implementation to highlight or support the next meeting is still missing here.
 * Currently, all meetings are simply returned as upcoming.
 */
fun scheduleOf(
    meetings: List<Meeting>,
    now: Instant,
): MeetingSchedule =
    // TODO: The implementation to highlight or support the next meeting is still missing.
    MeetingSchedule(
        next = null,
        upcoming = meetings,
        past = emptyList(),
    )
