package com.schwarz_digits.mobilecommunity

import androidx.compose.runtime.Composable
import com.schwarz_digits.mobilecommunity.core.ui.splashscreen.NativeLottieAnimation
import com.schwarz_digits.mobilecommunity.core.ui.splashscreen.rememberSplashScreenJson

@Composable
fun SplashScreenContainer(onAnimationFinished: () -> Unit) {
    val jsonContent = rememberSplashScreenJson()

    if (jsonContent != null) {
        NativeLottieAnimation(
            jsonContent = jsonContent,
            progress = 1f,
            onAnimationFinished = onAnimationFinished
        )
    }
}
