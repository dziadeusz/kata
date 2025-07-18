package kuc.karol.algorithms.kotlin.celebrity

class Party(private val guests: Set<Guest>) {
    val thisParty = this

    fun findCelebrityLinear(): Guest? {
        return thisParty hasACandidateAmongIts
                guests whoIsACelebrityOr null;

    }

    infix fun hasACandidateAmongIts(guests: Set<Guest>): CandidateSearchResult {
        val others = guests.toList().drop(1)
        val candidate = others.fold(guests.first()) { currentCandidate, nextGuest ->
            if (currentCandidate knows nextGuest) nextGuest else currentCandidate
        }
        return CandidateSearchResult(candidate, guests)
    }

    fun findCelebrityQuadratic(): Guest? {
        return guests.find { candidate ->
            guests.all { guest ->
                candidate == guest || candidate doesntKnow guest && guest knows candidate
            }
        }
    }
}

data class CandidateSearchResult(
    val candidate: Guest,
    val guests: Set<Guest>
) {
    infix fun whoIsACelebrityOr(default: Guest?): Guest? {
        val candidateIsCelebrity = guests
            .filter { it != candidate }
            .all { guest -> candidate doesntKnow guest && guest knows candidate }
        return if (candidateIsCelebrity) candidate else default
    }
}

data class CelebritySearchResult(
    val isSuccessFull: Boolean,
    val candidate: Guest
)

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
