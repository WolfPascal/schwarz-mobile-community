package com.schwarz_digits.mobilecommunity.core.ui.share

import androidx.compose.runtime.Composable

/**
 * Creates and remembers a platform-specific text sharing launcher function.
 * - Android: Launches an ACTION_SEND chooser intent with text/plain mime type.
 * - iOS: Presents a UIActivityViewController with the given text.
 */
@Composable
expect fun rememberTextShareLauncher(): (String) -> Unit
