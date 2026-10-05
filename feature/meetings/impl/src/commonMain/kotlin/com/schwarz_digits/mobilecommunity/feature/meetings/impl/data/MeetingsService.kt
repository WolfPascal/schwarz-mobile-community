package com.schwarz_digits.mobilecommunity.feature.meetings.impl.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Service responsible for fetching community meetings from the backend endpoint via Ktor.
 */
class MeetingsService(
    private val client: HttpClient,
) {
    suspend fun fetchMeetings(): List<MeetingDto> = client.get("https://api.schwarz-digits.local/v1/meetings").body()
}
