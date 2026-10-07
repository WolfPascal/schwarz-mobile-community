package com.schwarz_digits.mobilecommunity.core.ui.splashscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import com.schwarz_digits.mobilecommunity.core.ui.mock.MockDataProvider

@Composable
expect fun NativeLottieAnimation(
    jsonContent: String,
    progress: Float,
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit
)

@Composable
fun rememberSplashScreenJson(): String? {
    val splashJsonState = produceState<String?>(initialValue = null) {
        value = MockDataProvider.getSplashScreenJson()
    }
    return splashJsonState.value
}
