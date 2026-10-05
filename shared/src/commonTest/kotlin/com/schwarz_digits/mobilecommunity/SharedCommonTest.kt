package com.schwarz_digits.mobilecommunity

import com.schwarz_digits.mobilecommunity.core.model.MeetingFormat
import com.schwarz_digits.mobilecommunity.ui.navigation.NavigationDestination
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SharedCommonTest {
    @Test
    fun testNavigationDestinationsCoverage() {
        val destinations = NavigationDestination.entries
        assertEquals(2, destinations.size)
        destinations.forEach { destination ->
            assertNotNull(destination.titleRes)
            assertNotNull(destination.iconRes)
        }
    }

    @Test
    fun testMeetingFormatDisplayNames() {
        assertEquals("Hybrid", MeetingFormat.HYBRID.displayName)
        assertEquals("Virtual", MeetingFormat.VIRTUAL.displayName)
        assertEquals("Onsite", MeetingFormat.ONSITE.displayName)
    }
}
