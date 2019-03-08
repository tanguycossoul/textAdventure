/*
    Return the index of a value from a sorted array
 */
public class recursive {

    public static int search( int[] arr, int val, int min_index, int max_index ) {
        if (min_index <= max_index) {
            int mid_index = (max_index + min_index) / 2;

            if (val == arr[mid_index]) {
                return mid_index;

            } else if (val < arr[mid_index]) {
                return search(arr, val, min_index, mid_index - 1);

            } else { // val > arr[ mid_index ]
                return search(arr, val, mid_index + 1, max_index);
            }
        }
        else {
            return -1;
        }
    }
}
