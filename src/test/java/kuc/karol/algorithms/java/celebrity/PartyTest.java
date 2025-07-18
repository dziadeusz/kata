package kuc.karol.algorithms.java.celebrity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PartyTest {

    @Test
    public void findCelebrityLinearShouldReturnCelebrityWhenOneExists() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false, true, true},  // Person 0 knows Person 1 and 2
            {false, false, true}, // Person 1 knows Person 2
            {false, false, false} // Person 2 knows nobody
        };

        // when:
        Integer celebrity = party.findCelebrityLinear(whoKnowsWhom);

        // then:
        assertEquals(2, celebrity);
    }

    @Test
    public void findCelebrityLinearShouldReturnNullWhenNoCelebrityExists() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false, true, true},
            {true, false, true},
            {true, true, false}
        };

        // when:
        Integer celebrity = party.findCelebrityLinear(whoKnowsWhom);

        // then:
        assertNull(celebrity);
    }

    @Test
    public void findCelebrityLinearShouldHandleSinglePersonCase() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false} // Single person doesn't know themselves
        };

        // when:
        Integer celebrity = party.findCelebrityLinear(whoKnowsWhom);

        // then:
        assertEquals(0, celebrity);
    }

    @Test
    public void findCelebrityLinearShouldHandleEdgeCaseWhereEveryoneKnowsEveryone() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false, true, true},
            {true, false, true},
            {true, true, false}
        };

        // when:
        Integer celebrity = party.findCelebrityLinear(whoKnowsWhom);

        // then:
        assertNull(celebrity);
    }

    @Test
    public void findNoCelebrityLinearShouldHandleEdgeCaseWhereNobodyKnowsAnyone() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false, false, false},
            {false, false, false},
            {false, false, false}
        };

        // when:
        Integer celebrity = party.findCelebrityLinear(whoKnowsWhom);

        // then:
        assertEquals(null, celebrity);
    }
    @Test
    public void findCelebrityQuadraticShouldReturnCelebrityWhenOneExists() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false, true, true},  // Person 0 knows Person 1 and 2
            {false, false, true}, // Person 1 knows Person 2
            {false, false, false} // Person 2 knows nobody
        };

        // when:
        Integer celebrity = party.findCelebrityQuadratic(whoKnowsWhom);

        // then:
        assertEquals(2, celebrity);
    }

    @Test
    public void findCelebrityQuadraticShouldReturnNullWhenNoCelebrityExists() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false, true, true},
            {true, false, true},
            {true, true, false}
        };

        // when:
        Integer celebrity = party.findCelebrityQuadratic(whoKnowsWhom);

        // then:
        assertNull(celebrity);
    }

    @Test
    public void findCelebrityQuadraticShouldHandleSinglePersonCase() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false} // Single person doesn't know themselves
        };

        // when:
        Integer celebrity = party.findCelebrityQuadratic(whoKnowsWhom);

        // then:
        assertEquals(0, celebrity);
    }

    @Test
    public void findCelebrityQuadraticShouldHandleEdgeCaseWhereEveryoneKnowsEveryone() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false, true, true},
            {true, false, true},
            {true, true, false}
        };

        // when:
        Integer celebrity = party.findCelebrityQuadratic(whoKnowsWhom);

        // then:
        assertNull(celebrity);
    }

    @Test
    public void findNoCelebrityQuadraticShouldHandleEdgeCaseWhereNobodyKnowsAnyone() {
        // given:
        Party party = new Party();
        boolean[][] whoKnowsWhom = {
            {false, false, false},
            {false, false, false},
            {false, false, false}
        };

        // when:
        Integer celebrity = party.findCelebrityQuadratic(whoKnowsWhom);

        // then:
        assertEquals(null, celebrity);
    }
}
