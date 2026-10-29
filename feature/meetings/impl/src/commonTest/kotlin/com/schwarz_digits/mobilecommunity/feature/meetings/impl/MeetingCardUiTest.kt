package com.schwarz_digits.mobilecommunity.feature.meetings.impl

import androidx.compose.ui.test.ExperimentalTestApi
import com.schwarz_digits.mobilecommunity.core.model.Meeting
import com.schwarz_digits.mobilecommunity.core.model.MeetingFormat
import kotlin.time.Instant

/**
 * Shared Compose Multiplatform UI test for meeting card components.
 * Executes on both Android and iOS, validating component rendering and click interactions.
 */
@OptIn(ExperimentalTestApi::class)
class MeetingCardUiTest : BaseComposeTest() {
    val fakeMeeting =
        Meeting(
            id = "1",
            title = "Compose Multiplatform in Production",
            date = "Oct 22, 2026",
            time = "16:00 - 17:30 CET",
            format = MeetingFormat.HYBRID,
            location = "Schwarz Digits Campus",
            speaker = "Aleks Morgan",
            startsAt = Instant.parse("2026-10-22T14:00:00Z"),
            endsAt = Instant.parse("2026-10-22T15:30:00Z"),
            agendaUrl = "https://schwarz-digits.de",
        )

    // TODO: Add UI tests here for UpcomingMeetingTile / StandardMeetingCard (e.g. verifying title, date, and UI nodes)
}


