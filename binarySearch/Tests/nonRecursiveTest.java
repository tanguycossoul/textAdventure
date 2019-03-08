import org.junit.Test;
import static org.junit.Assert.*;

public class nonRecursiveTest {

    private static int[] arr = {5, 11, 22, 34, 43, 56, 61, 72, 89, 95};

    @Test
    public void searchTest() {
        // Found
        assertEquals(0, nonRecursive.search(arr, 5));
        assertEquals(3, nonRecursive.search(arr, 34));
        assertEquals(arr.length-1, nonRecursive.search(arr, 95));

        // Not Found
        assertEquals(-1, nonRecursive.search(arr, 4));
        assertEquals(-1, nonRecursive.search(arr, 6));
        assertEquals(-1, nonRecursive.search(arr, 94));
        assertEquals(-1, nonRecursive.search(arr, 96));
    }
}