public class insertionSort {

    public static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int locToInsert = i;

            while (locToInsert != 0 && arr[locToInsert - 1] > arr[locToInsert]) {
                swap(arr, locToInsert - 1, locToInsert);
                locToInsert--;
            }
        }
        return arr;
    }

    private static void swap (int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
