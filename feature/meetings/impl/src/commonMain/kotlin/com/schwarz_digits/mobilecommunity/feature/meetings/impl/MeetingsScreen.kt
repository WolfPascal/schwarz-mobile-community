package com.schwarz_digits.mobilecommunity.feature.meetings.impl

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.schwarz_digits.mobilecommunity.core.model.Meeting
import com.schwarz_digits.mobilecommunity.core.ui.components.EmptyStateView
import com.schwarz_digits.mobilecommunity.core.ui.components.ErrorStateView
import com.schwarz_digits.mobilecommunity.core.ui.components.LoadingStateView
import com.schwarz_digits.mobilecommunity.core.ui.resources.Res
import com.schwarz_digits.mobilecommunity.core.ui.resources.brand_schwarz_digits
import com.schwarz_digits.mobilecommunity.core.ui.resources.ic_share
import com.schwarz_digits.mobilecommunity.core.ui.resources.loading_meetings
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_action_join
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_action_share
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_badge_next
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_empty_subtitle
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_empty_title
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_headline
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_host_prefix
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_share_text
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_speaker_prefix
import com.schwarz_digits.mobilecommunity.core.ui.resources.meetings_subtitle
import com.schwarz_digits.mobilecommunity.core.ui.resources.state_error_subtitle
import com.schwarz_digits.mobilecommunity.core.ui.resources.state_error_title
import com.schwarz_digits.mobilecommunity.core.ui.share.rememberTextShareLauncher
import com.schwarz_digits.mobilecommunity.core.ui.theme.DigitsColors
import com.schwarz_digits.mobilecommunity.feature.meetings.impl.presentation.MeetingsUiState
import com.schwarz_digits.mobilecommunity.feature.meetings.impl.presentation.MeetingsViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MeetingsScreen(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
) {
    MeetingsScreen(
        contentPadding = contentPadding,
        modifier = modifier,
        viewModel = viewModel { MeetingsViewModel() },
    )
}

@Composable
internal fun MeetingsScreen(
    contentPadding: PaddingValues,
    modifier: Modifier,
    viewModel: MeetingsViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    val shareLauncher = rememberTextShareLauncher()
    val shareText = stringResource(Res.string.meetings_share_text)
    var selectedAgendaUrl by remember { mutableStateOf<String?>(null) }

    if (selectedAgendaUrl != null) {
        MeetingAgendaScreen(
            url = selectedAgendaUrl.orEmpty(),
            onBackClick = { selectedAgendaUrl = null },
            contentPadding = contentPadding,
            modifier = modifier,
        )
        return
    }

    val statusBarTop = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val topPadding =
        if (contentPadding.calculateTopPadding() > statusBarTop) {
            contentPadding.calculateTopPadding() + 16.dp
        } else {
            statusBarTop + 16.dp
        }

    LazyColumn(
        modifier =
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        contentPadding =
            PaddingValues(
                top = topPadding,
                bottom = contentPadding.calculateBottomPadding() + 96.dp,
                start = 20.dp,
                end = 20.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier =
                            Modifier
                                .width(10.dp)
                                .height(10.dp)
                                .background(DigitsColors.CyanPrimary, RoundedCornerShape(2.dp)),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(Res.string.brand_schwarz_digits),
                        style = MaterialTheme.typography.labelSmall,
                        color = DigitsColors.CyanPrimary,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(Res.string.meetings_headline),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(Res.string.meetings_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        when (val state = uiState) {
            is MeetingsUiState.Loading -> {
                item {
                    LoadingStateView(
                        message = stringResource(Res.string.loading_meetings),
                    )
                }
            }
            is MeetingsUiState.Empty -> {
                item {
                    EmptyStateView(
                        title = stringResource(Res.string.meetings_empty_title),
                        message = stringResource(Res.string.meetings_empty_subtitle),
                    )
                }
            }
            is MeetingsUiState.Error -> {
                item {
                    ErrorStateView(
                        title = stringResource(Res.string.state_error_title),
                        message = stringResource(Res.string.state_error_subtitle),
                        onRetry = { viewModel.loadMeetings() },
                    )
                }
            }
            is MeetingsUiState.Success -> {
                items(state.meetings, key = { it.id }) { meeting ->
                    if (meeting.isNext) {
                        NextMeetingHighlightCard(
                            meeting = meeting,
                            onShareClick = { shareLauncher(shareText) },
                            onAgendaClick = {
                                selectedAgendaUrl = meeting.agendaUrl ?: "https://schwarz-digits.de"
                            },
                        )
                    } else {
                        StandardMeetingCard(
                            meeting = meeting,
                            onShareClick = { shareLauncher(shareText) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NextMeetingHighlightCard(
    meeting: Meeting,
    onShareClick: () -> Unit,
    onAgendaClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = DigitsColors.NavyCard,
            ),
        border = BorderStroke(1.5.dp, DigitsColors.CyanPrimary),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    color = DigitsColors.CyanPrimary,
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Text(
                        text = stringResource(Res.string.meetings_badge_next),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = DigitsColors.NavyMidnight,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp,
                    )
                }

                Surface(
                    color = DigitsColors.CyanPrimary.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(0.5.dp, DigitsColors.CyanPrimary.copy(alpha = 0.4f)),
                ) {
                    Text(
                        text = meeting.format.displayName.uppercase(),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = DigitsColors.CyanPrimary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = meeting.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = DigitsColors.TextWhite,
                lineHeight = 26.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "📅 ${meeting.date} • ${meeting.time}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = DigitsColors.CyanLight,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "📍 ${meeting.location}",
                style = MaterialTheme.typography.bodyMedium,
                color = DigitsColors.TextMuted,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "🎙️ " + stringResource(Res.string.meetings_host_prefix, meeting.speaker),
                style = MaterialTheme.typography.bodyMedium,
                color = DigitsColors.TextMuted,
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = onAgendaClick,
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = DigitsColors.CyanPrimary,
                            contentColor = DigitsColors.NavyMidnight,
                        ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = stringResource(Res.string.meetings_action_join),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )
                }

                IconButton(
                    onClick = onShareClick,
                    modifier =
                        Modifier
                            .size(44.dp)
                            .background(
                                color = DigitsColors.CyanPrimary.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(10.dp),
                            ),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_share),
                        contentDescription = stringResource(Res.string.meetings_action_share),
                        tint = DigitsColors.CyanPrimary,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun StandardMeetingCard(
    meeting: Meeting,
    onShareClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    color = DigitsColors.CyanPrimary.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(0.5.dp, DigitsColors.CyanPrimary.copy(alpha = 0.35f)),
                ) {
                    Text(
                        text = meeting.format.displayName.uppercase(),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = DigitsColors.CyanPrimary,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Text(
                    text = meeting.date,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = DigitsColors.CyanPrimary,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = meeting.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "🕒 ${meeting.time} | 📍 ${meeting.location}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(Res.string.meetings_speaker_prefix, meeting.speaker),
                    style = MaterialTheme.typography.bodySmall,
                    color = DigitsColors.Neutral400,
                    modifier = Modifier.weight(1f),
                )

                IconButton(
                    onClick = onShareClick,
                    modifier = Modifier.size(36.dp),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_share),
                        contentDescription = stringResource(Res.string.meetings_action_share),
                        tint = DigitsColors.CyanPrimary,
                        modifier = Modifier.size(18.dp),
                    )
                }
            }
        }
    }
}
