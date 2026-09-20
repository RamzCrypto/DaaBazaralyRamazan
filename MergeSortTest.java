package daa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    public void testRandomArrays() {
        Random r = new Random(1);
        for (int t = 0; t < 100; t++) {
            int n = r.nextInt(200);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = r.nextInt(1000) - 500;

            int[] expected = a.clone();
            Arrays.sort(expected);

            Metrics m = new Metrics();
            MergeSort.sort(a, m);

            assertArrayEquals(expected, a, "test #" + t);
        }
    }
}