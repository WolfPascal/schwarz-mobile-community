package com.schwarz_digits.mobilecommunity.feature.meetings.impl

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import com.schwarz_digits.mobilecommunity.core.model.Meeting
import com.schwarz_digits.mobilecommunity.core.model.MeetingFormat
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Instant

/**
 * Shared Compose Multiplatform UI test for [NextMeetingHighlightCard].
 * Executes on both Android and iOS, validating component rendering and click interactions.
 */
@OptIn(ExperimentalTestApi::class)
class MeetingCardUiTest : BaseComposeTest() {
    private val fakeMeeting =
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

    @Test
    fun nextMeetingCard_displaysDetailsAndTriggersAgendaClick() =
        runComposeUiTest {
            var agendaClicked = false

            setContent {
                NextMeetingHighlightCard(
                    meeting = fakeMeeting,
                    onShareClick = {},
                    onAgendaClick = { agendaClicked = true },
                )
            }

            // 1. Verify header badge and title are displayed
            onNodeWithText("NEXT GATHERING").assertIsDisplayed()
            onNodeWithText("Compose Multiplatform in Production").assertIsDisplayed()

            // 2. Verify button label and perform click
            onNodeWithText("See agenda →").assertIsDisplayed()
            onNodeWithText("See agenda →").performClick()

            // 3. Verify interaction triggered the callback
            assertTrue(agendaClicked)
        }
}
