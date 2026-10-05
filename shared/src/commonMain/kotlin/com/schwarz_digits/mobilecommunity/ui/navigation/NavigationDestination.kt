package com.schwarz_digits.mobilecommunity.ui.navigation

import com.schwarz_digits.mobilecommunity.core.ui.resources.Res
import com.schwarz_digits.mobilecommunity.core.ui.resources.ic_meetings
import com.schwarz_digits.mobilecommunity.core.ui.resources.ic_news
import com.schwarz_digits.mobilecommunity.core.ui.resources.nav_meetings
import com.schwarz_digits.mobilecommunity.core.ui.resources.nav_news
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

/**
 * Top-level navigation destinations for the Schwarz Mobile Community application.
 */
enum class NavigationDestination(
    val titleRes: StringResource,
    val iconRes: DrawableResource,
) {
    NEWS(
        titleRes = Res.string.nav_news,
        iconRes = Res.drawable.ic_news,
    ),
    MEETINGS(
        titleRes = Res.string.nav_meetings,
        iconRes = Res.drawable.ic_meetings,
    ),
}
