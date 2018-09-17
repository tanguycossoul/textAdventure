import java.util.ArrayList;

public class hw_0907_accumulator {

    public static void main(String[] args) {
//        System.out.println( ex17_allDigitsWork(3906) );
//        System.out.println( ex18_isNarcissistic(1634) );
//        for (int val : ex19_gapsWithNoPrimes(10))
//            System.out.println( val );
        System.out.println( ex52_reciprocalSum(6) );
    }

    // returns true if all the digits of n are divisible by 3.
    public static boolean ex17_allDigitsWork(int n) { // 17, 18, 52, and 19
        while (n != 0) {
            if ((n % 10) % 3 != 0) {
                return false;
            }
            n /= 10;
        }
        return true;
    }

    // returns true iff n is a narcissistic number.
    public static boolean ex18_isNarcissistic(int n) {
        int len = 0;
        int n_orig = n;
        while (n != 0) {
            n /= 10;
            len++;
        }
        int sum = 0;
        n = n_orig;
        while (n != 0) {
            sum += Math.pow(n % 10, len);
            n /= 10;
        }
        return (sum == n_orig);
    }

    public static int[] ex19_gapsWithNoPrimes(int n) {
        int start = 2, stop = 3;
        int start_max = 2, stop_max = 3, gap_max = 0;
        while ( stop <= n ) {
            if ( isPrime(stop) || stop == n ) {
                if (stop - start > gap_max) {
                    start_max = start;
                    stop_max = stop;
                    gap_max = stop - start;
                }
                start = stop;
                stop = start + 1;
            }
            else {
                stop++;
            }
        }
        int[] res = new int[ stop_max - start_max + 1];
        for (int i = 0; i <= stop_max - start_max; i++) {
            res[i] = i + start_max;
        }
        return res;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static double ex52_reciprocalSum(int n) {
        double sum = 0;
        for (int i = 1; i <= n ; i++) {
            sum += 1.0/i;
        }
        return sum;
    }
}