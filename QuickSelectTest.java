package daa;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSelectTest {

    @Test
    public void testSelectRandom() {
        Random r = new Random(3);
        for (int t = 0; t < 100; t++) {
            int n = 1 + r.nextInt(200);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = r.nextInt(1000) - 500;

            int[] sorted = a.clone();
            Arrays.sort(sorted);

            int k = r.nextInt(n);
            Metrics m = new Metrics();
            int got = QuickSelect.select(a, k, m);

            assertEquals(sorted[k], got, "test #" + t + " k=" + k);
        }
    }
}