@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA")

package com.schwarz_digits.mobilecommunity.feature.meetings.impl

/**
 * Base test class bridging platform-specific test runners.
 * - Android: Annotates with @RunWith(RobolectricTestRunner::class) for JVM-based Compose rendering.
 * - iOS: Default native test runner.
 */
expect abstract class BaseComposeTest()
