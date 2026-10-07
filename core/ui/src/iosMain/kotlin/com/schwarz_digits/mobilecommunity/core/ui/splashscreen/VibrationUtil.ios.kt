package com.schwarz_digits.mobilecommunity.core.ui.splashscreen

import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle

fun triggerIosHaptic() {
    val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleHeavy)
    generator.prepare()
    generator.impactOccurred()
}
