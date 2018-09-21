public class hw_0921_week5_mixed_hw {

    public static void main(String[] args) {
        // Ex.1
//        int[] arr = new int[] {2, 3, 4, 4, 5};
//        System.out.println("Result: " + canBalance_fixed( arr ));

        // Ex.3
//        int[] arr = new int[] {3, 4, 6, 5, 2, 3};
//        displayArrayMask( arr );

        // Ex.4
        int[] arr = new int[] {1, 2, 1, 3};
        System.out.println("Result: " + isEverywhere( arr, 1) );
    }

    private static boolean canBalance_fixed(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int sumOnSide1 = getSumOnSide(arr, 0, i);
            int sumOnSide2 = getSumOnSide(arr, i+1, arr.length - 1); // FIXED: start is i+1
            if (sumOnSide1 == sumOnSide2) {
                return true;
            }
        }
        return false;
    }

    private static int getSumOnSide(int[] arr, int start, int end) {
        int sumOnSide = 0;
        for (int i = start; i <= end; i++) { // FIXED: <= end
            sumOnSide = sumOnSide + arr[i];
        }
        return sumOnSide;
    }

    private static void displayArrayMask( int[] arr ) {
        for (int i = 0; i < arr.length; i++){
            if (arr[i]%2 != 0)
                System.out.print( arr[i] + " ");
            else
                System.out.print(  "* " );
        }
    }

    private static boolean isEverywhere( int[] arr, int val ) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] != val && arr[i+1] != val) {
                return false;
            }

        }
        return true;
    }
}