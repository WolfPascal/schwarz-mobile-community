package com.schwarz_digits.mobilecommunity.core.ui.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.WebKit.WKWebView

// =================================================================================================
// TODO: Task 2 - Platform-Specific Implementation (iOS PlatformWebView)
//
// In Compose Multiplatform, native UI components are embedded using interop composables:
// - Android uses AndroidView (see `PlatformWebView.android.kt` as reference)
// - iOS uses UIKitView
//
// Native iOS WebKit basics:
// - WKWebView (platform.WebKit): The native iOS view rendering web content.
// - NSURL (platform.Foundation): Creates a native URL via NSURL.URLWithString(url).
// - NSURLRequest (platform.Foundation): Wraps the URL into a request via NSURLRequest.requestWithURL(nsUrl).
//
// Your Task:
// Complete the factory and update blocks below to instantiate WKWebView and load the target URL.
// =================================================================================================
@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformWebView(
    url: String,
    modifier: Modifier,
) {
    UIKitView(
        factory = {
            // TODO: Task 2 - Native View Factory
            // 'factory' is called once when the view enters composition.
            // Instantiate WKWebView and load the initial request for `url`.
            WKWebView().apply {
                // Hint: Convert `url` to NSURL, then call loadRequest(NSURLRequest.requestWithURL(...))
            }
        },
        modifier = modifier,
        update = { webView ->
            // TODO: Task 2 - View Update
            // 'update' is called on recomposition when state changes.
            // Synchronize the web view if `url` has changed.
        },
    )
}
