package com.schwarz_digits.mobilecommunity.feature.meetings.impl

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import com.schwarz_digits.mobilecommunity.core.model.Meeting
import com.schwarz_digits.mobilecommunity.core.model.MeetingFormat
import kotlin.test.Test

// =================================================================================================
// TODO: Task 3 - Compose Multiplatform UI Testing
//
// In Compose UI testing, tests interact with the Semantics tree using three core API categories:
// - Finders (androidx.compose.ui.test): Locate elements (e.g. `onNodeWithText(...)`, `onNodeWithTag(...)`)
// - Assertions (androidx.compose.ui.test / kotlin.test): Verify state or properties
//   (e.g. `.assertIsDisplayed()`, `.assertDoesNotExist()`, `assertTrue(...)`)
// - Actions (androidx.compose.ui.test): Simulate user interactions (e.g. `.performClick()`, `.performScrollTo()`)
//
// Your Task:
// 1. Verify that the card details (e.g. badge or meeting title) are displayed on the screen.
// 2. Locate the agenda button and simulate a click on it.
// 3. Verify that clicking the button triggered the `onAgendaClick` callback.
// =================================================================================================
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
            isNext = true,
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

            // TODO: Task 3.1 - Find a text node (e.g. badge or meeting title) and assert it is displayed

            // TODO: Task 3.2 - Find the action button ("See agenda →") and perform a click (performClick)

            // TODO: Task 3.3 - Assert that the click triggered the callback (agendaClicked is true)
        }
}
