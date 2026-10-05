package com.schwarz_digits.mobilecommunity.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Official Schwarz Digits brand color tokens (derived from schwarz-digits.de)
 */
object DigitsColors {
    // Signature Primary Cyan
    val CyanPrimary = Color(0xFF00C3CD)
    val CyanLight = Color(0xFF79DDE2)
    val CyanDark = Color(0xFF00969D)
    val CyanContainerDark = Color(0xFF003C3E)
    val CyanContainerLight = Color(0xFFE8FCFC)

    // Secondary & Deep Tech Navy Blues
    val NavyMidnight = Color(0xFF000E19) // Base background on schwarz-digits.de
    val NavyDark = Color(0xFF081723)
    val NavyCard = Color(0xFF0A1E2D) // Card background
    val NavyBorder = Color(0xFF22384A)
    val NavyElevated = Color(0xFF11212D)

    // Warm Orange Accent (used for highlights on schwarz-digits.de)
    val OrangeHighlight = Color(0xFFF07D00)

    // Neutrals
    val Neutral100 = Color(0xFFFAFBFC)
    val Neutral200 = Color(0xFFE3EAF0)
    val Neutral300 = Color(0xFFC9D6E0)
    val Neutral400 = Color(0xFF9DB3C4)
    val Neutral500 = Color(0xFF7393AB)
    val TextWhite = Color(0xFFFFFFFF)
    val TextMuted = Color(0xFFC9D6E0)
}

private val DarkColorScheme =
    darkColorScheme(
        primary = DigitsColors.CyanPrimary,
        onPrimary = DigitsColors.NavyMidnight,
        primaryContainer = DigitsColors.CyanContainerDark,
        onPrimaryContainer = DigitsColors.CyanLight,
        secondary = DigitsColors.CyanLight,
        onSecondary = DigitsColors.NavyMidnight,
        secondaryContainer = DigitsColors.NavyElevated,
        onSecondaryContainer = DigitsColors.TextWhite,
        tertiary = DigitsColors.OrangeHighlight,
        onTertiary = DigitsColors.TextWhite,
        background = DigitsColors.NavyMidnight,
        onBackground = DigitsColors.TextWhite,
        surface = DigitsColors.NavyCard,
        onSurface = DigitsColors.TextWhite,
        surfaceVariant = DigitsColors.NavyElevated,
        onSurfaceVariant = DigitsColors.TextMuted,
        outline = DigitsColors.NavyBorder,
        outlineVariant = Color(0x3300C3CD), // Subtle cyan border glow
    )

private val LightColorScheme =
    lightColorScheme(
        primary = DigitsColors.CyanDark,
        onPrimary = Color.White,
        primaryContainer = DigitsColors.CyanContainerLight,
        onPrimaryContainer = DigitsColors.CyanDark,
        secondary = DigitsColors.CyanPrimary,
        onSecondary = DigitsColors.NavyMidnight,
        secondaryContainer = DigitsColors.Neutral200,
        onSecondaryContainer = DigitsColors.NavyMidnight,
        tertiary = DigitsColors.OrangeHighlight,
        onTertiary = Color.White,
        background = DigitsColors.Neutral100,
        onBackground = DigitsColors.NavyMidnight,
        surface = Color.White,
        onSurface = DigitsColors.NavyMidnight,
        surfaceVariant = DigitsColors.Neutral200,
        onSurfaceVariant = DigitsColors.Neutral500,
        outline = DigitsColors.Neutral300,
        outlineVariant = Color(0x2600C3CD),
    )

@Composable
fun CommunityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
