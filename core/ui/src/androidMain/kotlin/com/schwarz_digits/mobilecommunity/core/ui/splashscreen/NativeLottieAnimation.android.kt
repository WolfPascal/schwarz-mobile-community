package com.schwarz_digits.mobilecommunity.core.ui.splashscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
actual fun NativeLottieAnimation(
    jsonContent: String,
    progress: Float,
    modifier: Modifier,
    onAnimationFinished: () -> Unit,
) {
    SplashScreen(jsonContent, onAnimationFinished)
}

@Composable
fun SplashScreen(
    jsonContent: String,
    onAnimationFinished: () -> Unit = {},
) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.Black),
    ) {
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween()),
        ) {
            SplashScreenAnimation(jsonContent, onAnimationFinished)
        }
    }
}

@Composable
private fun SplashScreenAnimation(
    jsonContent: String,
    onAnimationFinished: () -> Unit,
) {
    val context = LocalContext.current
    var hasVibrated by remember { mutableStateOf(false) }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.JsonString(jsonContent),
    )

    val logoAnimationState =
        animateLottieCompositionAsState(
            composition = composition,
            isPlaying = true,
        )

    val hapticMarker =
        remember(composition) {
            composition?.getMarker("haptic_impact")
        }

    val markerProgress =
        remember(composition, hapticMarker) {
            val comp = composition
            val marker = hapticMarker
            if (comp != null && marker != null && comp.durationFrames > 0) {
                (marker.startFrame - comp.startFrame) / comp.durationFrames
            } else {
                null
            }
        }

    LaunchedEffect(logoAnimationState.progress, markerProgress) {
        if (markerProgress != null && logoAnimationState.progress >= markerProgress && !hasVibrated) {
            hasVibrated = true
            // TODO: Vibrate
            context.vibratePhone()
        }
    }

    LottieAnimation(
        modifier = Modifier.fillMaxSize(),
        composition = composition,
        clipToCompositionBounds = false,
        progress = { logoAnimationState.progress },
        safeMode = true,
    )

    LaunchedEffect(logoAnimationState.isAtEnd) {
        if (logoAnimationState.isAtEnd && logoAnimationState.progress == 1f) {
            // TODO: Show Main Screen
            onAnimationFinished()
        }
    }
}

@Preview
@Composable
fun SplashScreePreview() {
    SplashScreen("") {}
}
