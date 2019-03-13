import java.util.Arrays;

// TODO: redo as a static function, add arr[] to mergeSort() and tmp() to merge()

public class MergeSort {
    private static int[] arr, tmp;

    public MergeSort(int[] arr) {
        this.arr = arr;
        tmp = new int[arr.length];
    }

    //    public static int[] mergeSort(int[] arr, int s1, int s2, int e2) {
    public int[] mergeSort(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(l, m);
            mergeSort(m + 1, r);

            merge(l, m, r);
        }
        return tmp;
    }


    private void merge(int l, int m, int r) {
        int s1 = l;
        int s2 = m+1;
        int nextLoc = l;

        while (s1 <= m && s2 <= r) {
            if (arr[s1] < arr[s2]) {
                tmp[nextLoc++] = arr[s1++];
            }
            else {
                tmp[nextLoc++] = arr[s2++];
            }
        }
        if (s1 > m) {
            System.arraycopy(arr, m, tmp, nextLoc, s2-m );
        }
        else {
            System.arraycopy(arr, l, tmp, nextLoc, m-l + 1);
        }

        System.out.println( Arrays.toString( tmp ) );
    }
}
