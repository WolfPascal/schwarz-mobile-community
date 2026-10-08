package com.schwarz_digits.mobilecommunity.feature.meetings.impl.domain

import com.schwarz_digits.mobilecommunity.core.model.Meeting
import com.schwarz_digits.mobilecommunity.core.model.MeetingFormat
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Instant

class MeetingScheduleTest {
    private val now = Instant.parse("2026-10-06T12:00:00Z")

    @Test
    fun noMeetingsResultsInAnEmptySchedule() {
        val schedule = scheduleOf(emptyList(), now)

        assertNull(schedule.next)
        assertEquals(emptyList(), schedule.upcoming)
        assertEquals(emptyList(), schedule.past)
    }

    @Test
    fun earliestFutureMeetingIsNext() {
        val later = meeting("later", startsIn = 30.days)
        val sooner = meeting("sooner", startsIn = 2.days)

        val schedule = scheduleOf(listOf(later, sooner), now)

        assertEquals(sooner, schedule.next)
    }

    @Test
    fun upcomingExcludesNextAndIsSortedEarliestFirst() {
        val third = meeting("third", startsIn = 60.days)
        val first = meeting("first", startsIn = 2.days)
        val second = meeting("second", startsIn = 30.days)

        val schedule = scheduleOf(listOf(third, first, second), now)

        assertEquals(listOf(second, third), schedule.upcoming)
    }

    @Test
    fun pastIsSortedMostRecentFirst() {
        val longAgo = meeting("longAgo", startsIn = -60.days)
        val recent = meeting("recent", startsIn = -2.days)

        val schedule = scheduleOf(listOf(longAgo, recent), now)

        assertEquals(listOf(recent, longAgo), schedule.past)
    }

    @Test
    fun runningMeetingIsStillNext() {
        val running = meeting("running", startsIn = -30.minutes)
        val future = meeting("future", startsIn = 2.days)

        val schedule = scheduleOf(listOf(future, running), now)

        assertEquals(running, schedule.next)
        assertEquals(emptyList(), schedule.past)
    }

    @Test
    fun meetingEndingExactlyNowIsPast() {
        val justEnded = meeting("justEnded", startsIn = -(1.hours))

        val schedule = scheduleOf(listOf(justEnded), now)

        assertNull(schedule.next)
        assertEquals(listOf(justEnded), schedule.past)
    }

    @Test
    fun onlyPastMeetingsMeansNoNext() {
        val past = meeting("past", startsIn = -7.days)

        val schedule = scheduleOf(listOf(past), now)

        assertNull(schedule.next)
        assertEquals(emptyList(), schedule.upcoming)
        assertEquals(listOf(past), schedule.past)
    }

    private fun meeting(
        id: String,
        startsIn: Duration,
    ): Meeting =
        Meeting(
            id = id,
            title = "Meeting $id",
            date = "",
            time = "",
            format = MeetingFormat.VIRTUAL,
            location = "",
            speaker = "",
            startsAt = now + startsIn,
            endsAt = now + startsIn + 1.hours,
        )
}
