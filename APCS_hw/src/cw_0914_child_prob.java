// Montecarloproblems set, exercise #6
 public class cw_0914_child_prob {
    private static final int TRIALS = 100000;
    private static final double BOY_PROB = 105.0/205;

    private static final boolean BOY = true;
    private static final boolean GIRL = false;

    public static void main(String[] args) {
        int count = 0;

        for (int i = 0; i < TRIALS; i++) {
            int boy_count = 0, girl_count =0;
            do {
                boolean gender = pickGender(BOY_PROB);
                if ( gender == BOY )
                    boy_count++;
                else
                    girl_count++;

                count++;
            } while ( boy_count < 1 || girl_count < 1 );
        }

        System.out.println("AVERAGE = " + (double) count/TRIALS);
    }

    private static boolean pickGender(double boy_prob) {
        return (Math.random() <= boy_prob);
    }
}