package com.schwarz_digits.mobilecommunity.core.ui.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformWebView(
    url: String,
    modifier: Modifier,
) {
    UIKitView(
        factory = {
            WKWebView().apply {
                val nsUrl = NSURL.URLWithString(url)
                if (nsUrl != null) {
                    loadRequest(NSURLRequest.requestWithURL(nsUrl))
                }
            }
        },
        modifier = modifier,
        update = { webView ->
            val nsUrl = NSURL.URLWithString(url)
            if (nsUrl != null && webView.URL?.absoluteString != url) {
                webView.loadRequest(NSURLRequest.requestWithURL(nsUrl))
            }
        },
    )
}
