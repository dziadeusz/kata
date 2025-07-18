package kuc.karol.algorithms.kotlin.celebrity

class Party(private val guests: Set<Guest>) {
    fun celebrityInvitedLinear(): Guest? {
        val candidate = this partyHasACelebrityCandidateAmong guests
        val candidateIsCelebrity = guests
            .filter { it != candidate }
            .all { guest -> candidate doesntKnow guest && guest knows candidate }
        return if(candidateIsCelebrity) candidate else null

    }

    infix fun partyHasACelebrityCandidateAmong(guests: Set<Guest>): Guest {
        val candidate = guests.first()
        val others = guests.toList().drop(1)
        return others.fold(candidate) { currentCandidate, nextGuest ->
            if (currentCandidate knows nextGuest) nextGuest else currentCandidate
        }
    }
}

data class Guest(val id: Int, val socialNetwork: Array<BooleanArray>) {

    infix fun knows(other: Guest) = socialNetwork[id][other.id]
    infix fun doesntKnow(other: Guest) = !(this knows other)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Guest

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result
        return result
    }
}
