package com.schwarz_digits.mobilecommunity.core.ui.share

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.uikit.LocalUIViewController
import platform.UIKit.UIActivityViewController
import platform.UIKit.popoverPresentationController

@Composable
actual fun rememberTextShareLauncher(): (String) -> Unit {
    val viewController = LocalUIViewController.current
    return remember(viewController) {
        { text ->
            val activityViewController =
                UIActivityViewController(
                    activityItems = listOf(text),
                    applicationActivities = null,
                )
            activityViewController.popoverPresentationController?.sourceView = viewController.view
            viewController.presentViewController(
                viewControllerToPresent = activityViewController,
                animated = true,
                completion = null,
            )
        }
    }
}
