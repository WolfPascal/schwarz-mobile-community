package com.schwarz_digits.mobilecommunity.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Platform-specific Bottom Navigation Bar.
 * - Android: Standard Material 3 NavigationBar
 * - iOS: Frosted Glass / Translucent Glass bar with native blur
 */
@Composable
expect fun PlatformBottomBar(
    currentDestination: NavigationDestination,
    onDestinationSelected: (NavigationDestination) -> Unit,
    modifier: Modifier = Modifier,
)
