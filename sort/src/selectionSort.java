public class selectionSort {

    public static int[] sort(int[] arr) {
        int min, min_ind = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    min_ind = j;
                }
            }
            swap(arr, i, min_ind);
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
