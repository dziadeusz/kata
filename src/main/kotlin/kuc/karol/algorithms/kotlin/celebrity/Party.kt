package kuc.karol.algorithms.kotlin.celebrity

private val knowNoOneButAllKnowHim: (Guest, Guest) -> Boolean =
    { aCelebrity, otherGuest ->
        aCelebrity doesntKnow otherGuest && otherGuest knows aCelebrity
    }
private val thereIsNoCelebrity = null

class CelebrityLinearFinder(val party: Party) {
    fun findCelebrityLinear() =
        party hasACandidateAmongIts party.guests whoMight
                knowNoOneButAllKnowHim asACelebrityOr thereIsNoCelebrity;
}

data class Party(val guests: Set<Guest>) {

    fun findCelebrityQuadratic(): Guest? {
        return guests.find { celebrity ->
            guests.all { guest ->
                val isCelebrity = celebrity doesntKnow guest && guest knows celebrity
                celebrity == guest || isCelebrity
            }
        }
    }

    infix fun hasACandidateAmongIts(guests: Set<Guest>): CandidateSearchResult {
        val others = guests.toList().drop(1)
        val candidate = others.fold(guests.first()) { currentCandidate, nextGuest ->
            if (currentCandidate knows nextGuest) nextGuest else currentCandidate
        }
        return CandidateSearchResult(candidate, guests)
    }
}

data class CandidateSearchResult(
    val candidate: Guest,
    val guests: Set<Guest>
) {
    infix fun whoMight(celebrityRule: (Guest, Guest) -> Boolean): CelebritySearchResult {
        val candidateIsCelebrity = guests
            .filter { it != candidate }
            .all { guest -> celebrityRule(candidate, guest) }
        return CelebritySearchResult(candidateIsCelebrity, candidate)
    }
}

data class CelebritySearchResult(
    val isSuccessFull: Boolean,
    val candidate: Guest
) {
    infix fun asACelebrityOr(default: Guest?): Guest? =
        if (isSuccessFull) candidate else default

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
