package com.schwarz_digits.mobilecommunity.feature.meetings.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarz_digits.mobilecommunity.core.ui.network.NetworkClientProvider
import com.schwarz_digits.mobilecommunity.feature.meetings.impl.data.MeetingsRepository
import com.schwarz_digits.mobilecommunity.feature.meetings.impl.data.MeetingsService
import com.schwarz_digits.mobilecommunity.feature.meetings.impl.domain.MeetingSchedule
import com.schwarz_digits.mobilecommunity.feature.meetings.impl.domain.scheduleOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Clock

/**
 * UI state representing the current screen presentation for Meetings.
 */
sealed interface MeetingsUiState {
    data object Loading : MeetingsUiState

    data class Success(
        val schedule: MeetingSchedule,
    ) : MeetingsUiState

    data object Empty : MeetingsUiState

    data class Error(
        val message: String? = null,
    ) : MeetingsUiState
}

/**
 * ViewModel managing the asynchronous loading and state transitions of community meetings.
 */
class MeetingsViewModel(
    private val repository: MeetingsRepository =
        MeetingsRepository(
            MeetingsService(NetworkClientProvider.createClient()),
        ),
    private val clock: Clock = Clock.System,
) : ViewModel() {
    private val _uiState = MutableStateFlow<MeetingsUiState>(MeetingsUiState.Loading)
    val uiState: StateFlow<MeetingsUiState> = _uiState.asStateFlow()

    init {
        loadMeetings()
    }

    fun loadMeetings() {
        _uiState.value = MeetingsUiState.Loading
        viewModelScope.launch {
            repository
                .getMeetings()
                .mapCatching { meetings -> scheduleOf(meetings, clock.now()) }
                .onSuccess { schedule ->
                    _uiState.value =
                        if (schedule.next == null && schedule.upcoming.isEmpty() && schedule.past.isEmpty()) {
                            MeetingsUiState.Empty
                        } else {
                            MeetingsUiState.Success(schedule)
                        }
                }.onFailure { error ->
                    _uiState.value = MeetingsUiState.Error(error.message)
                }
        }
    }
}
