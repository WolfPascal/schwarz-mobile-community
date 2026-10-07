package com.schwarz_digits.mobilecommunity.core.ui.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.UIKitView
import platform.UIKit.UIView
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("LottieSplashHelper", exact = true)
object LottieSplashHelper {
    var lottieViewFactory: ((jsonContent: String, onFinished: () -> Unit) -> UIView)? = null
}

//TODO: Implement vibration
@Composable
actual fun NativeLottieAnimation(
    jsonContent: String,
    progress: Float,
    modifier: Modifier,
    onAnimationFinished: () -> Unit,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.Black),
    ) {

        val factory = LottieSplashHelper.lottieViewFactory
        if (factory != null) {
            UIKitView(
                factory = {
                    factory(jsonContent, onAnimationFinished)
                },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
