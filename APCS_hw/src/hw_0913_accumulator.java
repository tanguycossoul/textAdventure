public class hw_0913_accumulator {

    public static void main(String[] args) {
//        System.out.println( ex17_allDigitsWork(3906) );
//        System.out.println( ex18_isNarcissistic(1634) );
//        for (int val : ex19_gapsWithNoPrimes(10))
//            System.out.println( val );
//        System.out.println( ex52_reciprocalSum(6) );
        for (int val : ex41_zeroFront( new int[] {0, 7, 6, 4, 0, 2, 0, 0, 5, 2, 1} ) )
            System.out.print( val + " ");
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

    public static double ex52_reciprocalSum(int n) {
        double sum = 0;
        for (int i = 1; i <= n ; i++) {
            sum += 1.0/i;
        }
        return sum;
    }

    private static int[] ex41_zeroFront( int[] arr_in ) {
        int[] arr_out = new int[ arr_in.length ];
        int index_zero = 0, index_nonZero = arr_in.length - 1;
        for (int val : arr_in) {
            if (val == 0)
                arr_out[index_zero++] = 0;
            else
                arr_out[index_nonZero--] = val;
        }
        return arr_out;
    }
}