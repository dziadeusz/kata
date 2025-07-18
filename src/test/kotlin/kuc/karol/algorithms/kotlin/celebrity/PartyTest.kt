package kuc.karol.algorithms.kotlin.celebrity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class PartyTest {

    @Test
    fun `celebrityInvitedLinear should return celebrity when one exists`() {
        // given:
        val socialNetwork = arrayOf(
            booleanArrayOf(false, true, true),  // Person 0 knows Person 1 and 2
            booleanArrayOf(false, false, true), // Person 1 knows Person 2
            booleanArrayOf(false, false, false) // Person 2 knows nobody
        )

        val guests = (0 until 3).map { id -> Guest(id, socialNetwork) }.toSet()
        val party = Party(guests)

        // when:
        val celebrity = party.celebrityInvitedLinear()

        // then:
        assertEquals(guests.find { it.id == 2 }, celebrity)
    }

    @Test
    fun `celebrityInvitedLinear should return null when no celebrity exists`() {
        // given:
        val socialNetwork = arrayOf(
            booleanArrayOf(false, true, true),  // Person 0 knows Person 1 and 2
            booleanArrayOf(true, false, true),  // Person 1 knows Person 0 and 2
            booleanArrayOf(true, true, false)   // Person 2 knows Person 0 and 1
        )

        val guests = (0 until 3).map { id -> Guest(id, socialNetwork) }.toSet()
        val party = Party(guests)

        // when:
        val celebrity = party.celebrityInvitedLinear()

        // then:
        assertNull(celebrity)
    }

    @Test
    fun `celebrityInvitedLinear should handle single person case`() {
        // given:
        val socialNetwork = arrayOf(
            booleanArrayOf(false)  // Single person doesn't know themselves
        )

        val guests = (0 until 1).map { id -> Guest(id, socialNetwork) }.toSet()
        val party = Party(guests)

        // when:
        val celebrity = party.celebrityInvitedLinear()

        // then:
        assertEquals(guests.first(), celebrity)
    }

    @Test
    fun `celebrityInvitedLinear should handle edge case where everyone knows everyone`() {
        // given:
        val socialNetwork = arrayOf(
            booleanArrayOf(false, true, true),  // Person 0 knows Person 1 and 2
            booleanArrayOf(true, false, true),  // Person 1 knows Person 0 and 2
            booleanArrayOf(true, true, false)   // Person 2 knows Person 0 and 1
        )

        val guests = (0 until 3).map { id -> Guest(id, socialNetwork) }.toSet()
        val party = Party(guests)

        // when:
        val celebrity = party.celebrityInvitedLinear()

        // then:
        assertNull(celebrity)
    }

    @Test
    fun `celebrityInvitedLinear should handle edge case where nobody knows anyone`() {
        // given:
        val socialNetwork = arrayOf(
            booleanArrayOf(false, false, false),  // Person 0 knows nobody
            booleanArrayOf(false, false, false),  // Person 1 knows nobody
            booleanArrayOf(false, false, false)   // Person 2 knows nobody
        )

        val guests = (0 until 3).map { id -> Guest(id, socialNetwork) }.toSet()
        val party = Party(guests)

        // when:
        val celebrity = party.celebrityInvitedLinear()

        // then:
        // In this case, no one is a celebrity because no one is known by everyone else
        assertNull(celebrity)
    }
}
