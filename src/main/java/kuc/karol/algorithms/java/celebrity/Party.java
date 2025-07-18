package kuc.karol.algorithms.java.celebrity;

public class Party {

    public Integer findCelebrityLinear(boolean[][] knows) {
        Integer candidate = 0;
        for (int i = 1; i < knows.length; i++) {
            if (knows[candidate][i]) {
                candidate = i;
            }
        }

        for (int i = 0; i < knows.length; i++) {
            if (i == candidate) {
                continue;
            }
            if (knows[candidate][i] || !knows[i][candidate]) {
                candidate = null;
                break;
            }
        }
        return candidate;
    }

    public Integer findCelebrityQuadratic(boolean[][] knows) {
        for(int i = 0; i < knows.length; i++){
            boolean knowsSomeone = false;
            boolean somebodyDoesntKnow = false;
            for(int j = 0; j < knows.length; j++){
                if (i == j) {
                    continue;
                }
                knowsSomeone = knows[i][j];
                somebodyDoesntKnow = !knows[j][i];
                if(knowsSomeone || somebodyDoesntKnow) {
                    break;
                }
            }
            if(!knowsSomeone && !somebodyDoesntKnow) {
                return i;
            }
        }
        return null;
    }
}
