package com.schwarz_digits.mobilecommunity

import com.schwarz_digits.mobilecommunity.ui.navigation.NavigationDestination
import kotlin.test.Test
import kotlin.test.assertNotNull

class SharedLogicAndroidHostTest {
    @Test
    fun testAndroidNavigationInitialization() {
        NavigationDestination.entries.forEach { destination ->
            assertNotNull(destination.titleRes)
            assertNotNull(destination.iconRes)
        }
    }
}
