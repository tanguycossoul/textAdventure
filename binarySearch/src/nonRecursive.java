/*
    Return the index of a value from a sorted array
 */
public class nonRecursive {

    public static int search( int[] arr, int val ) {
        int min_index = 0;
        int max_index = arr.length;
        int mid_index;
        do {
            mid_index =  (max_index + min_index) / 2;
            if (val == arr[ mid_index ]) {
                return mid_index;
            }
            else if (val < arr[ mid_index ]) {
                max_index = mid_index - 1;
            }
            else { // val > arr[ mid_index ]
                min_index = mid_index + 1;
            }
        } while (mid_index != 0 && mid_index != arr.length-1);
        return -1;
    }
}
