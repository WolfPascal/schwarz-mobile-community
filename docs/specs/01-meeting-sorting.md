# Specification: Chronological Meeting Sorting & Grouping

## Goal & Scope
Implement deterministic chronological sorting and grouping in `scheduleOf` to transform a flat list of `Meeting` models into a structured `MeetingSchedule`. The function must partition meetings relative to a given `now: Instant` into a single highlighted next meeting, upcoming meetings sorted ascending, and past meetings sorted descending.

## Affected files / components
- Domain logic: `feature/meetings/impl/src/commonMain/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/domain/MeetingSchedule.kt`
- Domain model: `core/model/src/commonMain/kotlin/com/schwarz_digits/mobilecommunity/core/model/Meeting.kt`
- UI presentation: `feature/meetings/impl/src/commonMain/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/MeetingsScreen.kt`
- ViewModel: `feature/meetings/impl/src/commonMain/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/presentation/MeetingsViewModel.kt`
- Unit tests: `feature/meetings/impl/src/commonTest/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/domain/MeetingScheduleTest.kt`

## Business rules & sorting logic
- **Time Representation**: All timestamps are UTC-based `kotlin.time.Instant` values (`startsAt`, `endsAt`). Comparisons are evaluated directly on `Instant` instances, ensuring timezone independence.
- **Classification Relative to `now`**:
  - **Active / Not Ended**: A meeting is active if `endsAt > now`. Meetings currently in progress (`startsAt <= now < endsAt`) are considered active.
  - **Past / Completed**: A meeting is past if `endsAt <= now`.
- **Grouping & Ordering**:
  - `next`: The active meeting (`endsAt > now`) with the earliest `startsAt`. If multiple active meetings exist, the one with the smallest `startsAt` is selected. If no active meetings exist, `next` is `null`.
  - `upcoming`: All active meetings excluding `next`, sorted in ascending order by `startsAt` (earliest meeting first).
  - `past`: All completed meetings (`endsAt <= now`), sorted in descending order by `startsAt` (most recent meeting first).

## Edge cases
- **Identical Timestamps**: If two meetings share the identical `startsAt`, tie-break deterministically using `id` in natural ascending order (`compareBy({ it.startsAt }, { it.id })`).
- **Empty List**: When `meetings` is empty, return `MeetingSchedule(next = null, upcoming = emptyList(), past = emptyList())`.
- **Only Past Meetings**: When all meetings have `endsAt <= now`, `next` is `null`, `upcoming` is `emptyList()`, and `past` contains all meetings sorted descending by `startsAt`.
- **Only Future Meetings**: `next` holds the earliest meeting, `upcoming` holds all other meetings sorted ascending, and `past` is `emptyList()`.
- **Meeting Ending Exactly at `now`**: A meeting with `endsAt == now` is classified as `past`, not active.
- **Missing or Malformed Timestamps**: `startsAt` and `endsAt` are non-nullable `kotlin.time.Instant` fields guaranteed valid by the data layer.

## Acceptance criteria
- [ ] **Given** an empty list of meetings, **when** `scheduleOf(emptyList(), now)` is called, **then** `next` is `null`, `upcoming` is empty, and `past` is empty.
- [ ] **Given** multiple future meetings in arbitrary order, **when** `scheduleOf(meetings, now)` is called, **then** `next` is the meeting with the lowest `startsAt`, and `upcoming` contains the remaining future meetings sorted in ascending order of `startsAt`.
- [ ] **Given** a meeting currently in progress (`startsAt < now` and `endsAt > now`) and future meetings, **when** `scheduleOf(meetings, now)` is called, **then** the in-progress meeting is selected as `next`.
- [ ] **Given** multiple past meetings (`endsAt <= now`), **when** `scheduleOf(meetings, now)` is called, **then** `past` contains all past meetings ordered descending by `startsAt` (most recent first).
- [ ] **Given** meetings with identical `startsAt` values, **when** `scheduleOf(meetings, now)` is called, **then** ordering remains deterministic using `id` ascending as a tie-breaker.
