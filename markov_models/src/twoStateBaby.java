public class TwoStateBaby {

    static final String[] stateNames = {"awake", "sleeping"};
    static final int AWAKE = 0;
    static final int SLEEPING = 1;
    private int currentState;
    double[][] transitions = {{0.3, 0.7},
            {0.4, 0.6}};

    public TwoStateBaby(int startState) {
        currentState = startState;
    }

    // return most probable next state from currentState
    public int mostLikelyNextState() {
        int largestprob = 0;
        double currentgreatestprob = 0;
        int currentgreatestprobfinalcol = 0;
        for (int col = AWAKE; col < transitions[currentState].length; col++) {

            if (transitions[currentState][col] > currentgreatestprob) {
                currentgreatestprob = transitions[currentState][col];
            }
            currentgreatestprobfinalcol = col;
        }
        return currentgreatestprobfinalcol;
    }

    // return a next state determined by the probability table given the currentState
    public int getNextState() {
        double nextstate = Math.random();
        for (int i = 0; i < transitions[currentState].length; i++) {

        }
        if (nextstate > transitions[currentState][]
    }

    public void transitionToNextState() {
        /* YOU COMPLETE FOR PART C */
    }

    // display a sequence of n states starting with
// currentState that is determined by the probability
// table.
    public void displayBabyStates(int n) {
    }
}
