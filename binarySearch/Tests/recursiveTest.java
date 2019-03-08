import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class recursiveTest {

    private static int[] arr = {5, 11, 22, 34, 43, 56, 61, 72, 89, 95};

    @Test
    public void searchTest() {
        // Found
        assertEquals(0, recursive.search(arr, 5, 0, arr.length-1));
        assertEquals(3, recursive.search(arr, 34, 0, arr.length-1));
        assertEquals(arr.length-1, recursive.search(arr, 95, 0, arr.length-1));

        // Not found
        assertEquals(-1, recursive.search(arr, 4, 0, arr.length-1));
        assertEquals(-1, recursive.search(arr, 6, 0, arr.length-1));
        assertEquals(-1, recursive.search(arr, 94, 0, arr.length-1));
        assertEquals(-1, recursive.search(arr, 96, 0, arr.length-1));
    }
}