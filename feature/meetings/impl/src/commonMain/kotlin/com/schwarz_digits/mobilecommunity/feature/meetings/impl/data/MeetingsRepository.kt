package com.schwarz_digits.mobilecommunity.feature.meetings.impl.data

import com.schwarz_digits.mobilecommunity.core.model.Meeting
import com.schwarz_digits.mobilecommunity.core.model.MeetingFormat

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
                    isNext = dto.isNext,
                    agendaUrl = dto.agendaUrl,
                )
            }
        }

    private fun parseMeetingFormat(format: String): MeetingFormat = MeetingFormat.valueOf(format.uppercase())
}
