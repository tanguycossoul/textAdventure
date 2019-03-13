import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class selectionSortTest {

    private int[] arr1 = {24, 6, 17, 98, 37, 43, 52, 88, 61, 73};
    private int[] arr2 = {6, 17, 24, 37, 43, 52, 61, 73, 88, 98};
    private int[] arr3 = {6, 6, 6, 6, 6, 6, 6, 6};

    @Test
    public void sort() {
        assertArrayEquals(arr2, selectionSort.sort( arr1 ));
        assertArrayEquals(arr2, selectionSort.sort( arr2 ));
        assertArrayEquals(arr3, selectionSort.sort( arr3 ));
//        System.out.println(Arrays.toString( arr1 ));
//        System.out.println(Arrays.toString( selectionSort.sort( arr1 ) ));
    }
}