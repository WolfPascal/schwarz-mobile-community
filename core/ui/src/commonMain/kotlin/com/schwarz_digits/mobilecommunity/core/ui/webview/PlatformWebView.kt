package com.schwarz_digits.mobilecommunity.core.ui.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Platform-agnostic WebView component rendering native web views.
 * - Android: [android.webkit.WebView] hosted via [androidx.compose.ui.viewinterop.AndroidView].
 * - iOS: [platform.WebKit.WKWebView] hosted via [androidx.compose.ui.interop.UIKitView].
 */
@Composable
expect fun PlatformWebView(
    url: String,
    modifier: Modifier = Modifier,
)
