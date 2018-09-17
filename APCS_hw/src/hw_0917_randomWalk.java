import java.util.*;

public class hw_0917_randomWalk {
    private static final int NUMSTEPS_MAX = 100;
    private static final int NUM_TRIALS = 100000;

    public static void main(String[] args) {
        double[] distance_per_steps_arr = new double[NUMSTEPS_MAX + 1];

        for (int num_steps = 1; num_steps <= NUMSTEPS_MAX; num_steps++) {
            double distance_total = 0;
            for (int trail_num = 0; trail_num < NUM_TRIALS; trail_num++) {
                distance_total += randomWalk(num_steps);
            }
            double distance_avg = distance_total / NUM_TRIALS;
            distance_per_steps_arr[ num_steps ] = distance_avg;
        }

        printTable(distance_per_steps_arr);
    }

    private static void printTable(double[] distance_per_steps_arr) {
        System.out.println("Steps,Distance");
        for (int steps = 1; steps <= NUMSTEPS_MAX; steps++) {
            System.out.println(steps + "," + distance_per_steps_arr[ steps ]);
        }
    }

    private static double randomWalk(int steps) {
        int x = 0, y = 0;
        for (int i = 0; i < steps; i++) {
            int direction = (int) (Math.random() * 4);

            // #ASSUMPTION: grid has no boundaries
            if      (direction == 0) x++;  // go right
            else if (direction == 1) x--;  // go left
            else if (direction == 2) y++;  // go up
            else if (direction == 3) y--;  // go down
        }
 //       double distance = calculateDistanceEuclidian(x, y);
        return calculateDistanceSteps(x, y);
    }

    private static double calculateDistanceSteps(int x, int y) {
        return Math.abs(x) + Math.abs(y);
    }

    private static double calculateDistanceEuclidian(int x, int y) {
        return Math.sqrt( Math.pow(x, 2) + Math.pow(y, 2) );
    }
}