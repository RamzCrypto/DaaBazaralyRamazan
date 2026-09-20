package daa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    public void testRandomArrays() {
        Random r = new Random(2);
        for (int t = 0; t < 100; t++) {
            int n = r.nextInt(200);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = r.nextInt(1000) - 500;

            int[] expected = a.clone();
            Arrays.sort(expected);

            Metrics m = new Metrics();
            QuickSort.sort(a, m);

            assertArrayEquals(expected, a, "test #" + t);
        }
    }

    @Test
    public void testDepthOnSorted100k() {
        int n = 100000;
        int[] a = InputGenerator.sorted(n);
        Metrics m = new Metrics();
        QuickSort.sort(a, m);

        double limit = 2 * (Math.log(n) / Math.log(2));
        assertTrue(m.maxDepth <= limit,
                "maxDepth=" + m.maxDepth + " limit=" + limit);
    }
}