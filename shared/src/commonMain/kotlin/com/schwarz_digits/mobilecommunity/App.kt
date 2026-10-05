package com.schwarz_digits.mobilecommunity

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schwarz_digits.mobilecommunity.core.ui.theme.CommunityTheme
import com.schwarz_digits.mobilecommunity.feature.meetings.api.MeetingsFeatureEntry
import com.schwarz_digits.mobilecommunity.feature.meetings.impl.MeetingsFeatureEntryImpl
import com.schwarz_digits.mobilecommunity.feature.news.api.NewsFeatureEntry
import com.schwarz_digits.mobilecommunity.feature.news.impl.NewsFeatureEntryImpl
import com.schwarz_digits.mobilecommunity.ui.navigation.NavigationDestination
import com.schwarz_digits.mobilecommunity.ui.navigation.PlatformBottomBar

@Composable
fun App(
    newsFeatureEntry: NewsFeatureEntry = remember { NewsFeatureEntryImpl() },
    meetingsFeatureEntry: MeetingsFeatureEntry = remember { MeetingsFeatureEntryImpl() },
) {
    CommunityTheme {
        var currentDestination by remember { mutableStateOf(NavigationDestination.NEWS) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                PlatformBottomBar(
                    currentDestination = currentDestination,
                    onDestinationSelected = { currentDestination = it },
                )
            },
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                Crossfade(targetState = currentDestination) { destination ->
                    when (destination) {
                        NavigationDestination.NEWS -> {
                            newsFeatureEntry.NewsContent(contentPadding = paddingValues)
                        }
                        NavigationDestination.MEETINGS -> {
                            meetingsFeatureEntry.MeetingsContent(contentPadding = paddingValues)
                        }
                    }
                }
            }
        }
    }
}
