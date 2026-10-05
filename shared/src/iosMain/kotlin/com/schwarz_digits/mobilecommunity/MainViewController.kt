package com.schwarz_digits.mobilecommunity

import androidx.compose.ui.window.ComposeUIViewController
import com.schwarz_digits.mobilecommunity.core.ui.theme.CommunityTheme
import com.schwarz_digits.mobilecommunity.feature.meetings.api.MeetingsFeatureEntry
import com.schwarz_digits.mobilecommunity.feature.meetings.impl.MeetingsFeatureEntryImpl
import com.schwarz_digits.mobilecommunity.feature.news.api.NewsFeatureEntry
import com.schwarz_digits.mobilecommunity.feature.news.impl.NewsFeatureEntryImpl
import platform.UIKit.UIViewController

private val defaultNewsFeatureEntry: NewsFeatureEntry by lazy { NewsFeatureEntryImpl() }
private val defaultMeetingsFeatureEntry: MeetingsFeatureEntry by lazy { MeetingsFeatureEntryImpl() }

/**
 * Fallback entry point for pre-iOS 26 or standalone full-Compose hosting.
 */
fun MainViewController(): UIViewController =
    ComposeUIViewController {
        App(
            newsFeatureEntry = defaultNewsFeatureEntry,
            meetingsFeatureEntry = defaultMeetingsFeatureEntry,
        )
    }

/**
 * Entry point for News screen, embedded in SwiftUI TabView for iOS 26 Liquid Glass.
 */
fun NewsViewController(): UIViewController = NewsViewController(defaultNewsFeatureEntry)

/**
 * Entry point for News screen with custom feature entry.
 */
fun NewsViewController(entry: NewsFeatureEntry): UIViewController =
    ComposeUIViewController {
        CommunityTheme {
            entry.NewsContent()
        }
    }

/**
 * Entry point for Meetings screen, embedded in SwiftUI TabView for iOS 26 Liquid Glass.
 */
fun MeetingsViewController(): UIViewController = MeetingsViewController(defaultMeetingsFeatureEntry)

/**
 * Entry point for Meetings screen with custom feature entry.
 */
fun MeetingsViewController(entry: MeetingsFeatureEntry): UIViewController =
    ComposeUIViewController {
        CommunityTheme {
            entry.MeetingsContent()
        }
    }
