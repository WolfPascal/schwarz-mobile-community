# Specification 01: Meeting Sorting & Categorization

## Goal & Scope
Implement chronological sorting and categorization in `scheduleOf` to transform a raw list of `Meeting` entities into a populated `MeetingSchedule` data class relative to a reference time `now: Instant`. Testing is strictly out of scope for this task.

## Affected files / components
- Domain logic: `feature/meetings/impl/src/commonMain/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/domain/MeetingSchedule.kt` (function `scheduleOf`)
- Domain model: `core/model/src/commonMain/kotlin/com/schwarz_digits/mobilecommunity/core/model/Meeting.kt` (`startsAt: Instant`, `endsAt: Instant`)
- Presentation State: `feature/meetings/impl/src/commonMain/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/presentation/MeetingsViewModel.kt`
- UI Screen: `feature/meetings/impl/src/commonMain/kotlin/com/schwarz_digits/mobilecommunity/feature/meetings/impl/MeetingsScreen.kt`

## Business rules
- **Reference Time**: Evaluate all meetings against `now: Instant` using UTC instant comparison.
- **Categorization**:
  - **Past (`past`)**: Meetings that ended at or before `now` (`endsAt <= now`).
  - **Active / Upcoming**: Meetings that have not ended yet (`endsAt > now`). Meetings in progress (`startsAt <= now < endsAt`) count as active.
- **Sorting & Extraction**:
  - **`next`**: The single active meeting with the earliest `startsAt`. Extracted as a single `Meeting?` item and excluded from the `upcoming` list. If there are no active meetings, `next` is `null`.
  - **`upcoming`**: All remaining active meetings after extracting `next`, sorted chronologically in ascending order (`startsAt` earliest first).
  - **`past`**: All past meetings, sorted reverse-chronologically in descending order (`startsAt` most recent first).
- **Target Data Class**: Return `MeetingSchedule(next: Meeting?, upcoming: List<Meeting>, past: List<Meeting>)`.

## Edge cases
- **Empty input list**: Return `MeetingSchedule(next = null, upcoming = emptyList(), past = emptyList())`.
- **All meetings in the past**: `next` is `null`, `upcoming` is `emptyList()`, and `past` contains all meetings sorted descending by `startsAt`.
- **All meetings in the future**: `next` is the single earliest meeting, `upcoming` contains all other meetings sorted ascending by `startsAt`, and `past` is `emptyList()`.
- **Single active meeting**: `next` is that meeting, `upcoming` is `emptyList()`, and `past` contains any past meetings.
- **Identical `startsAt` timestamps**: Tie-break deterministically using `id` in ascending order (`compareBy({ it.startsAt }, { it.id })`).
- **Meeting ending exactly at `now`**: Categorized as `past` (`endsAt <= now`).

## Acceptance criteria
- [ ] **Given** a raw list of meetings and current time `now`, **when** `scheduleOf(meetings, now)` is called, **then** `MeetingSchedule` is populated with `next`, `upcoming`, and `past`.
- [ ] **Given** active meetings exist, **when** categorized, **then** `next` contains exactly one `Meeting` (the closest future meeting) and is excluded from `upcoming`.
- [ ] **Given** no active meetings exist, **when** categorized, **then** `next` is `null` and `upcoming` is empty.
- [ ] **Given** multiple upcoming meetings, **when** categorized, **then** `upcoming` is sorted chronologically in ascending order of `startsAt`.
- [ ] **Given** multiple past meetings, **when** categorized, **then** `past` is sorted reverse-chronologically in descending order of `startsAt`.
- [ ] **Given** an empty list of meetings, **when** `scheduleOf(emptyList(), now)` is called, **then** `next` is `null`, `upcoming` is empty, and `past` is empty.
- [ ] **Given** the new `MeetingSchedule` output, **when** observed by `MeetingsViewModel`, **then** the UI displays the next highlighted meeting and section lists deterministically.
