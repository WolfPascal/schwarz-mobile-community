# Specification 02: Meeting Unit & Compose UI Tests

## Goal & Scope
Implement automated test coverage using `kotlin.test` for the meeting categorization logic (`scheduleOf`) and `androidx.compose.ui.test` for the `UpcomingMeetingTile` / `StandardMeetingCard` Compose Multiplatform component.

## Target Files
- Unit Tests: `feature/meetings/impl/src/commonTest/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/domain/MeetingScheduleTest.kt`
- UI Tests: `feature/meetings/impl/src/commonTest/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/MeetingCardUiTest.kt`
- Target UI Composable: `feature/meetings/impl/src/commonMain/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/MeetingsScreen.kt` (`UpcomingMeetingTile` / `StandardMeetingCard`)

## Technical Rules
- **Unit Testing (`kotlin.test`)**:
  - Create the test class `MeetingScheduleTest` in package `com.schwarz_digits.mobilecommunity.feature.meetings.impl.domain`.
  - Use standard `kotlin.test` assertions (`assertEquals`, `assertNull`, `assertTrue`).
  - Pass a deterministic, frozen `Instant` for `now` (e.g., `Instant.parse("2026-10-06T12:00:00Z")`) to ensure repeatable results without relying on system time.
  - Test the distribution of meetings across `next`, `upcoming`, and `past`, including edge cases.
- **Compose Multiplatform UI Testing (`androidx.compose.ui.test`)**:
  - Implement tests in `MeetingCardUiTest` extending `BaseComposeTest` using `runComposeUiTest { ... }`.
  - Use the pre-existing `fakeMeeting` fixture in `MeetingCardUiTest`.
  - Test `UpcomingMeetingTile` (or `StandardMeetingCard`) and assert that the meeting title, date, and format badge are displayed using `onNodeWithText(...).assertIsDisplayed()`.
  - If necessary, `Modifier.testTag()` can be added to elements for targeted node queries.

## Acceptance Criteria
- [ ] **Given** a frozen `now` timestamp and mixed meetings, **when** `scheduleOf` is evaluated, **then** unit tests verify `next`, `upcoming`, and `past` are populated with the correct items in the expected order.
- [ ] **Given** only past meetings (`endsAt <= now`), **when** `scheduleOf` is evaluated, **then** unit tests verify `next` is `null`, `upcoming` is empty, and `past` contains all meetings sorted descending.
- [ ] **Given** an empty list of meetings, **when** `scheduleOf` is evaluated, **then** unit tests verify `next` is `null`, `upcoming` is empty, and `past` is empty.
- [ ] **Given** `UpcomingMeetingTile` / `StandardMeetingCard` rendered inside `runComposeUiTest`, **when** the UI tree is inspected, **then** the UI test verifies the meeting title and date are displayed on screen.
- [ ] **Given** the test suite is executed, **when** tests run, **then** all unit tests and UI tests pass successfully.
