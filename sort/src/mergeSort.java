import java.util.Arrays;

public class mergeSort {

    public static void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, tmp);
    }


    private static void mergeSort(int arr[], int left, int right, int[] tmp) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(arr, left, middle, tmp);
            mergeSort(arr, middle + 1, right, tmp);
            merge(arr, left, middle, middle + 1, right, tmp);
        }
    }

    private static void merge(int[] arr, int s1, int e1, int s2, int e2, int[] tmp) {
        int left_ind = s1, right_ind = s2, tmp_ind = 0;

        while ( left_ind <= e1 && right_ind <= e2) {
            if (arr[left_ind] < arr[right_ind]) {
                tmp[tmp_ind++] = arr[left_ind++];
            }
            else {
                tmp[tmp_ind++] = arr[right_ind++];
            }
        }

        if (left_ind <= e1) { // rigt sub-array ran out of numbers, copy the rest of the left numbers into tmp
            System.arraycopy(arr, left_ind, tmp, tmp_ind, e1 - left_ind + 1);
        }
        else {
            System.arraycopy(arr, right_ind, tmp, tmp_ind, e2 - right_ind + 1);
        }

        System.arraycopy(tmp, 0, arr, s1, e2 - s1 + 1);
        System.out.println( "copying tmp to arr at position " + s1 + ": " + Arrays.toString( tmp ) ); // for debug
    }
}
