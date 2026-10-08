package com.schwarz_digits.mobilecommunity.feature.meetings.impl.data

import com.schwarz_digits.mobilecommunity.core.model.Meeting
import com.schwarz_digits.mobilecommunity.core.model.MeetingFormat
import kotlin.time.Instant

/**
 * Repository for accessing community gatherings, mapping network DTOs to domain entities.
 */
class MeetingsRepository(
    private val meetingsService: MeetingsService,
) {
    suspend fun getMeetings(): Result<List<Meeting>> =
        runCatching {
            meetingsService.fetchMeetings().map { dto ->
                Meeting(
                    id = dto.id,
                    title = dto.title,
                    date = dto.date,
                    time = dto.time,
                    format = parseMeetingFormat(dto.format),
                    location = dto.location,
                    speaker = dto.speaker,
                    startsAt = Instant.parse(dto.startsAt),
                    endsAt = Instant.parse(dto.endsAt),
                    agendaUrl = dto.agendaUrl,
                )
            }
        }

    private fun parseMeetingFormat(format: String): MeetingFormat = MeetingFormat.valueOf(format.uppercase())
}
